package com.gurukulams.starter.security.security.oauth2;

import com.gurukulams.core.model.Learner;
import com.gurukulams.core.service.LearnerService;
import com.gurukulams.starter.security.exception.OAuth2AuthenticationProcessingException;
import com.gurukulams.starter.security.model.AuthProvider;
import com.gurukulams.starter.security.security.CustomUserDetailsService;
import com.gurukulams.starter.security.security.UserPrincipal;
import com.gurukulams.starter.security.security.oauth2.user.OAuth2UserInfo;
import com.gurukulams.starter.security.security.oauth2.user.OAuth2UserInfoFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

/**
 * The type Custom o auth 2 user service.
 */
@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    /**
     * User Details Service.
     */
    @Autowired
    private LearnerService userRepository;

    /**
     * Loads the user.
     *
     * @param oAuth2UserRequest
     * @return OAuth2User
     * @throws OAuth2AuthenticationException
     */
    @Override
    public OAuth2User loadUser(final OAuth2UserRequest oAuth2UserRequest)
            throws OAuth2AuthenticationException {
        final OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);

        try {
            return processOAuth2User(oAuth2UserRequest, oAuth2User);
        } catch (final AuthenticationException ex) {
            throw ex;
        } catch (final Exception ex) {
            // Throwing an instance of AuthenticationException will
            // trigger the OAuth2AuthenticationFailureHandler
            throw new InternalAuthenticationServiceException(ex.getMessage(),
                    ex.getCause());
        }
    }

    private OAuth2User processOAuth2User(final
                                         OAuth2UserRequest oAuth2UserRequest,
                                         final OAuth2User oAuth2User) {
        final OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory
                .getOAuth2UserInfo(oAuth2UserRequest.getClientRegistration()
                        .getRegistrationId(), oAuth2User.getAttributes());
        if (StringUtils.isEmpty(oAuth2UserInfo.getEmail())) {
            throw new OAuth2AuthenticationProcessingException(
                    "Email not found from OAuth2 provider");
        }

        final Optional<Learner> userOptional =
                userRepository.readByEmail(oAuth2UserInfo.getEmail());
        Learner user;
        if (userOptional.isPresent()) {
            user = userOptional.get();
            if (!user.provider().equals(AuthProvider.valueOf(
                    oAuth2UserRequest.getClientRegistration()
                            .getRegistrationId()))) {
                throw new OAuth2AuthenticationProcessingException(
                        "Looks like you're signed up with "
                                + user.provider()
                                + " account. Please use your "
                                + user.provider()
                                + " account to login.");
            }
            user = updateExistingUser(user, oAuth2UserInfo);
        } else {
            user = registerNewUser(oAuth2UserRequest, oAuth2UserInfo);
        }

        return UserPrincipal.create(user, oAuth2User.getAttributes());
    }

    private Learner registerNewUser(final OAuth2UserRequest oAuth2UserRequest,
                                    final OAuth2UserInfo oAuth2UserInfo) {
        final Learner user = new Learner(null,"tom",oAuth2UserInfo.getEmail(),oAuth2UserInfo.getImageUrl()
                ,null,oAuth2UserRequest.getClientRegistration()
                .getRegistrationId(),oAuth2UserInfo.getId(),"Tom",null,null);

        return userRepository.create(user);
    }

    private Learner updateExistingUser(final Learner existingUser,
                                    final OAuth2UserInfo oAuth2UserInfo) {

        return userRepository.update(existingUser.id(),existingUser);
    }

}
