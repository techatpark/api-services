package com.techatpark.starter.security.controller;

import com.techatpark.starter.security.model.AuthenticationRequest;
import com.techatpark.starter.security.model.AuthenticationResponse;
import com.techatpark.starter.security.utils.TokenUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authentication",
        description = "Resource to manage authentication")
public class AuthenticationApiController {

    /**
     * instance of authenticationManager.
     */
    private final AuthenticationManager authenticationManager;
    /**
     * instance of userDetailsService.
     */
    private final UserDetailsService userDetailsService;
    /**
     * instance of tokenUtil.
     */
    private final TokenUtil tokenUtil;

    /**
     * constructs authenticationManager,userDetailsService,tokenUtil.
     *
     * @param anAuthenticationManager
     * @param anUserDetailsService
     * @param aTokenUtil
     */
    public AuthenticationApiController(final AuthenticationManager
                                               anAuthenticationManager,
                                       final UserDetailsService
                                               anUserDetailsService,
                                       final TokenUtil aTokenUtil) {
        this.authenticationManager = anAuthenticationManager;
        this.userDetailsService = anUserDetailsService;
        this.tokenUtil = aTokenUtil;
    }

    /**
     * performs the login function.
     *
     * @param authenticationRequest
     * @return authentication response
     */
    @Operation(summary = "Login with credentials")
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(
            final @RequestBody @Valid
                    AuthenticationRequest
                    authenticationRequest) {

        Authentication authResult = this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getUserName(),
                        authenticationRequest.getPassword()));
        if (authResult == null) {
            throw new BadCredentialsException("Invalid Login Credentials");
        }

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUserName());
        final String token = tokenUtil.generateToken(userDetails);
        final String profilePicture = userDetails.getUsername().equals("tom")
                ? "data:image/png;base64,"
                + "iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ixAAAAAXNSR0IArs4"
                +
                "c6QAAAERlWElmTU0AKgAAAAgAAYdpAAQAAAABAAAAGgAAAAAAA6ABAAMAAAA"
                + "BAAEAAKACAAQAAAABAAAAMqADAAQAAAABAAAAMgAAAAB1y6+rAAAVyklEQV"
                + "RoBa1aCXhV5Zl+z7l7bpJ7s5F9IxAChEWWAAEUFKggCtJqrehQxam1M+3U9"
                +
                "ulYfdoa25lWp88zM7UrZZB22qIwtSBaUAHZQVAEwhKyk33fbra7n3m/c3OzA"
                + "Gnp8j/Pn3vOf/7z/9/6ft"
                + "/3nyj4OzVtFxzQsIbLLYKCKfydxO5gj2KX1svew1"
                + "4BTS0FlNNQovcpD3fJ2N/clL9lBe1NpCGIB8jAevZlXMt0O"
                + "+tpwdAsRYEPKo5"
                +
                "wjT0w2fYqnx6sv533bzXnr2JEZ8CP75L4TVxUvdXCI2PcQtOogJERudUbxxRy"
                + "I8/KGozBk7WZv77mn/yd//jx/r+YoVHLj2w03pW2G0648U0+/wqZsI03b"
                + "/S4J"
                + "lQK4Yp+NfoRYAD63Sp2nonBJ+6VcE5cAG/T2cHNk"
                + "/e8OiXT8rLyYHf32BfGv7"
                + "ttRrTXaULAdhIVO/5yNz/xaSrcXhMiLT6yNGRTMo1MNLYp"
                + "+NmZXHjTH0ZmZg7"
                + "Kis9idfTvcO/sPgQ1dKpa4Anlc9h786o3j"
                + "/wZswi94Plf9XkYlD1QVDKhkB"
                + "d57U"
                + "/JgMavmOnTBpTW2zHgNnD2qPmqynEDXjk5H6bcpzB5Yi7q6mqR5dmNV"
                +
                "fkuwB+AGgjEcqM92g48fzPZN4+MWv3mh0VFRVZvV802zXX10RnOchRmu5AZH"
                +
                "9AnajT08V6WJ5pRwfkqKzweAxZNHuQ7ARkFjPSHOhWvnl2A1LmPINYRRfPyoPz"
                + "kayi652MkRnPqsBMN07QDKdisLKdhj9PGowVFn/+81Z+WcciRlFJoUq1oa6"
                + "qHq+YsptnPY3VeMzITyVCApqLbf2h10ZRGU1KNQVypt+FSjQGfWTAAo0mY"
                +
                "py2RwMYODUUHpiF1/uOIdcbCyOFzH53G2ug3sH6BB5pfxBCGtVFUKziFZNwz"
                +
                "HjOUz61bfGT/tpQIb+HJiyehOhORlpWH5PQNaGy+Cy+fPYSlzlNYN6cXdusQM"
                + "8KRZiITGioazTh6yYiHl7rR2WtEQ3ckSht8aOtTcbTMhj5HBvwVV1FH5nxB"
                +
                "H3orjyPtHqDbZUC01U8kC9M0fCECK0Sjsg0Ibgw/Hf07aubI8OCv1Od/fWbm"
                +
                "9x9a+xxdI4hTl6/iUHk9ItJykJySCb/Pj7JrV2BtfAtfWFSDySl+2jXfNyko"
                +
                "qTPixTetmDPZAY8xGtVNbjjVdsRHDOJQRQrmrtqI5LRUqNzZYrbiysXzWGzY"
                +
                "hs8t8cMz6IeZXKjcc1y7VfCC8hh+MEJt6IqKHdu03xgeMFq0radKgkpm1jLE"
                + "x0QhNy0Ns9LiUF52Fddbu+GMTSBDyfDYp2HfmQEkWJqQHqvgeKkN//ZuOlz"
                +
                "2WTAmzkdFRyRsEXYkJ6ejK5AAf1QWHFGR8PmDiIyIhGowovzcYfzD7HLERgZ"
                +
                "hJDgoQXIojIzf7i7agPMv7Qazg5E2RiMSJ7RepVIxKbE/eTce967+d2QkxiBA"
                +
                "2w6H7IPnivF+JQnPK4AlAhgcDKD+0mGg9Rh6IgqQPXMpImwKNWnDheJivLf/j"
                + "7DY7Fi0eAlm5efD5+lHX18P2js6YCWTNR+9jdc316CmzYyKFhNWzXAjwkL1"
                + "iu+N3zqZ+OQoD2I4zozRSNEatYjvrlSo91NVEcibshJREVZdywIkGtWem5Y"
                +
                "MJ5351NViRDpTaR5GxKROgidiOlSzBU5KvLq6EVFRUdi29efo7GhDXm4uBvtc"
                +
                "cPX3YfKUPMTETUByajqsVhvMkXGobwNe3tWBH+7vRzt9akW+ASaVADE+MzZ4"
                +
                "oLy0BwfDvA7HEe23tjRG36+IioI+4o8hCiaqPtxkokpuxCwW5uXhwalZaKosR"
                +
                "lDhE0av1NQkTJw4iZcaGhpq4fF50OPqwcJFS7Dp80/ioUcexXRqpK+vTydQ4zu"
                +
                "OaAemzCyAN+dpBOLzYTIase2whp2nKF+Fe2ukRpi5VWd2of2Wud5QG2YEgcHv"
                +
                "kgqbQEZPnwarLQU2izk8b+SXa3sCASydlY8FE6LQWFfBOEnADAZhMEdAMVlgt"
                +
                "JgQEREBs8mM6dNnQlVNXNaIxMQkxMTECd9683q8qCm/TPm5MSl3CtavewCPP"
                + "rUZ//exCYNeTmFaM26TFCnAfG+o6YzonIUSQA5r6GBwtUemwG6zEPrHLhY"
                +
                "gI6I1IUxiQG15KQIBgSwD5wZhJwP2CGrTZKWW0mG2MsKToK7udlw69yE62pv"
                +
                "5vhEGMidjkyZmwt3XiWnT8jFnUQHmzJ2PlGmrcY2R/8+loyR1U1grIY34sY70i"
                +
                "uWQDw39PpUSTaT9j3EhPmRg5pwAV6hpqkVVmwt5M+ch4B+N/UBmVhaM5HLKlF"
                +
                "xmG37dVPvbr+KhNRGIt5SjqaYYYloqNZlJc0xPFwsJ0i8i0F51DNZoD94vi4Y25"
                +
                "CZC1zhdJTGSAw7xHFTWCezp0MeXAwEzkcdxE5QLnwbmSSeLr+D3h46LvyF2QgK"
                +
                "lT6lLkcHdGAWQRBNSOW9S7lT+GuH1eTEh1oRly3LxzFNzseouoLL0DMqvlaCxvg"
                +
                "5T6HPNtWWoqyjG/Jl2FD13F650O9EhmBRgthBUddrCNI7+RUBZL4wYtV0xDri"
                +
                "6l8lNuEmaYVDpH0L5qCYmJQSXt7ahxhtExOAgHJqBQhHTGpks5higH2VlZxO1O"
                +
                "rhXkBHchp6ebjjsUbhr6UT4fBU4cdoMm43VgGJAwYJCWoEd7t6jiEtz4N7756OS"
                +
                "iWR8NJ0lbM+jaBm+ZEEnPKjoc0l5Gg4TugOoqgceL8UhlI9qodRBQ5QtEua4LM"
                +
                "RmT4M/XO6NmieXwozZbKaDJzLXMqHfY0dldRdVSsY9QSxfnIM7ZloRH58EA81we"
                +
                "v4dhOVkggwrY7cbdy7NRxvfEfC5gYwbdiLt5EGlEReKSYjNSieeIsoYwEBfKzxM"
                +
                "p8NNFvMzSdy+/xiOl1QgNjYW9qhYqnz8KKwDBQkRTzNZYvHxJx1EN2pPxkj8rHw"
                +
                "rerrqqBA6P++NRjN8bgsCXg8cDmYFMQnURlC3Ap220XTyOky38MBUVT8okLX1Lla"
                +
                "eFq+gteUMbbRft/UwMz7mWJkJ0UiItiNoMP85SemvCSwH2OPpS9eqjCi+3ESi6Tfe"
                +
                "AImWfa4g6GeCT8JMZhMZJfQO+DAw4IaRqMmihvuwD6klTGeYJn2cPNDrlRyNWgh35i"
                +
                "NgGMCcuFocPXOCi0gVoVcSBAAr5s3IRUtbK9qvfYJeV+fwBuGFw79Si4ufNDU3ob"
                +
                "T0GtoJuwkpU/He+/UcD+Ljsip84+RuvHnsGNz9LsYiFWbGLYPNifLaZnx9325crG"
                +
                "LI595Bmq/QFwqQIVp1lwwphUFazRH4lSOb4SYaC1BCBdkeoH03jpy7DLNhSBzyM"
                +
                "GjAw4sX4jNz81BfWUYCRqJ/eBHdpHhjpOQFWjMzM9DLiG4hoR19saiqbEdtZzs"
                +
                "bEXx4Wq0NjUyk1D1+YJylzsacbGtAXXNzAJUmvzQwkPWxDuODJEU9NNwAyqdXc6d"
                +
                "ZGa4c4aBvmKgSh+c3Yb6qz/B3hOn4WO6IFITraycPw+DPh+8nKfcEH2FCR+f9ff3w+"
                +
                "VysUL06FE+Iz1dLwmczIRPf9wIG4FA6VbQ2e5Cl6ubqbtBjysKS+T23j7UvzsApZ0"
                +
                "FIdcXH5PsQcxIijefZkRLtxkN7Va0uyygJUYZxWFG0xIyIr5JULVx8cfmteFYxS"
                +
                "+wY89losynkJeTRc/VcK60GvHJWXpqIrPDTUzKRJSSXwOl3NnZBffgABNEK/x"
                + "0Yisjf32/DTl5QTijDQgywmoBMsG9BNpVZgwOhfVuZz+aGJTfuGJHnLEbWZ"
                + "G9MBOEOlwmuIkvqU4fizCmUiYvheKHoeg+fJVEMCEfaaJCaRq1QEEgJ96H"
                + "NEsFyqsv4EJlI85ebWbA8iJjaj41L6Y19IL+FqWnM8EMlgxFRtkR7WCR5f"
                +
                "Fh0O1DRekVeAiv8cycVcUBKw0iKT4BMQlxhGsL6htq4OusZ75nwbI1m9AUnIr"
                +
                "fnejFb04O4HINTwNnAfmpA3DYgrCZ/ODRABWhdVMjSg/piBuiQf8ZMr+Qiwu6"
                +
                "sk+IUrDG2YKalv14/lgepq/6AiwMmnpElxfIy4g1c4BCEMSSMTG/eBIaT4LjnF"
                +
                "FoaqxGVt59eOCz+TD8qwFdXV3Y//YelJaU4I45d6BPnYO7F+TgscefoJn60dH"
                +
                "ZAu+sAnRePsBzgFKmMhZm5goirT4WY3SjIIQRVJKMiToHN/wRexfp6o2EBhlkX"
                +
                "7+SjIxFn0VcdAzPq9y6P/gCXiJYr56ii3/46BdaUE5NWPExPsg6ft4b5Ygo4Ea"
                +
                "Mw4bYuFjCLVGKVeGECYms9c3Ysu1XeMjVT5KAy8UX8fiTTyE2JpYgEYkgkS6zc"
                +
                "B12V34IrfYs0p1+9NHGVMVEzQcrJekv5T4rb+Bh+DaEQHQ0zjxRYUapZQUWpm"
                +
                "UR8vw6vArhfgbO2Ng4Zrup+nse9wBa6qpYJifw8KGXZuVGQkIqM2E7Dh54Dxn"
                +
                "pc5lY0vnJRLitfWAdmTFi6y9/icrKKmzZ8guWyKkovviJDr+TxYwJ53HxKbh6p"
                +
                "R/L4k6yzKBHa5Su0VQqBn6K/Z/DC976V+GBg4L91UmYUjhPl7bMkxREz5V4LQx"
                +
                "rQ8c4NtbjfuZgGzdtZKXowPXq63j/4AEUvfgtHc2OHz9Kc+rGF5/5EqZOnaZvGU"
                +
                "PJb9z4OJYvv1PXcmbmJDQ01uKNnbswISmN+MK4RA3bIohYGWtxvKESn8puon9Ip"
                +
                "POcVrQtjCN+tJGOkXxLX3rkj0BfaRPw09oHMGXhWh3lGDfZqFrivhifBK0w/KmU"
                +
                "dE1VKe5cvAD33b9BX+j69WoUFhaiqalZv5c/SczDFhYuwLy5cxFN5iX1H2BNH2G"
                +
                "PZLVowUdnTiMndybBwomyshK4O+vgjPDCaLbD2HoVX5t3iUexATpKMMGoPI0e7"
                +
                "Sc4QmJ08yJDlHhor2H/oKyrumwwxE6i+k20c6YXHhVe/wB8gx3UhkoojYaBKCX8"
                +
                "BRhfpC4vuVJCRkJrpfIkZmFBAT5+ex/6aFFdNM3mlhbs2b1X7/Pm3oE9b72N5KRk"
                +
                "uHq70NnaiqrqGngZuz46/A7QfARPzenHomwvK1SeGzvtjEvcS9OOGMmDmBYlye8T"
                +
                "Q36iuzYdPOQbISKkwKnonYC4aSnobOtG3fVrUNsvwOauRUNHEGV9DsxccCdmzymgJB"
                +
                "lDaAT2yCgM9njQ3NSAJNq6jD/1xCasvFKJbKMNf+huwSf9vTjXxyyb3C9btizkY5Sk0"
                +
                "xmH48dPo6OmBEm1O/H13EZkF/hgldSLqYpkGlkJAwQAAwM3z6S5QIgRs22vNjj4Y0"
                +
                "IUwWykKZSsNC+LGzmjMlRcQkrvMTyeUINp84CYKA3VrQb0BQfQ6j+Ii+cvoFrJgDV"
                +
                "5BuImJNOH7Dj8wQf4HG1f2vLVa/HaeweRdOAjfC8xF22sHp9vKME7Xa3IysjQ55AGN"
                +
                "NTWovbAj/BMymHMziANErGFFil7RMgyk0erBmIhr/bKra4AuQj8SH2Ns5+Q63BTJVHj"
                +
                "a4MBI3aet2HxJB8mOdw8YCC/1JJugwI8shydzsu5rgEVZxqi8PuaSaj3JmDA1YGlS5d"
                +
                "gw0MPo6BgIepbmrBz8xdx91USHxmBMlaPG6rOYvsf38aqFatw8vhxXNr1VXw65RMkOLm"
                +
                "/7BNySF7c0BRsV/4FT8roMCPaq0ijVsvIrf4BR4QgeEAbI50S4XnNwSC1I8xJkAvJRo6c"
                +
                "VZYxPEWRN5iW/PGyET+/PBUZswuRlJSOgf5u1FRWYMbMPDy+6QkKU8Xebxch6eQlHvC5"
                +
                "UPWpJdj80kv44L192Lv3HcSY3YgKdmNOdDVW53ZjQrSkMboehKJwG+S2ucpXUC8Do8b"
                +
                "J+H/jZY49JxWAMHC7TVjSU2zmSz89YcSRwEosXnEfcV7qC4qBQggw3ry583UWbJ341os"
                +
                "vYendyxkPLlNj/Vi89C6cP3sKRd/5Nu7d8CghNpLB1sua/hq6ivdh49QKrMmTAwbaVpg"
                +
                "sBa8oX9W/nulkjmFE285Paz2M9H/BVylZVzQXZNx49agNh/3LcffaNUwjjGMSSgl+7a"
                +
                "0teHffHmRkZCF3cg6WL1uOrIkTUUqCPzh0CEG+k545kSk97ZWUmRnt29o7cfLAO1hi/"
                +
                "RBfWsIDcTvtOIBObpujPDtyZDqGEWFN+zGPVwI6it30TJ4PN3KgmxwHRCM/O2"
                +
                "HDCXUNCu9Zoafcut8MTw4J0kACG3mgIAd3tEC0EmLdAwNMQSw8FsrlGbFYNcVC"
                +
                "s+3v7UEbDzlSUgkePLY9f6EYvrJ9eCa/SiuYhPXKlwO6k4e3uCWx2n/pn7u+H5"
                +
                "50q1+JHXJqrtCctn5ow2nj/ZhXuJwWxtKWj1Qe4YxuojnpAs4+pvNmMTuuId1"
                +
                "IaJa1pC6Xspij6O3pQumlj5E7dSrTIQsSUpPR2t6Fio8OvvCbP+y76bPC2N2G"
                +
                "dqbKfsD9dowmZPS1EKQ7OlFtx/lIXIz8DOYtuYdMUe18yKJOfz4ElMOvitTkZ"
                +
                "N/I2pw5oB6rDJzc3HQdly+cQyU/W/iZp0m24CdDCUlJuH/dBjhjzKipKCGKRe"
                +
                "+4FROywS0ZkQdMXDaTGsnDxjTBAIXSJozj18VOnI38NKbPW0jEYmnMJujGIEWA"
                +
                "o8GNAgxhQnooIyOLnCfd4xlEydWLWDB/FubMmoaq8itobqyD3e5A36Ab/X2DWLF"
                +
                "iNfLzp59qbanfLHvcqt3StMIT6fw8r8E27v5oeIxiZ3piwP9cjEd14npkT5sBhT"
                +
                "V+iEQJKmOXFD8SgseOhrQh8bejvZWZQjm+9E9f5hGtBV3tHdi16w3YWSbI4c"
                +
                "bSJUuQP332joioiM3Z2dmsfW/dxteIkPQE3IQ4frNTX/ArYjAqPzWr2HI+Hg"
                +
                "1pDyNv2kyY9ODKR6IB3f2F9JHOA09qiPdSZHFOuOvkkDthcICpvo8n837mX47Y"
                +
                "GDyy8RH09Xfy5KVDa2xufGH6zOkb/xQTstafZETfjH+UZ4M/MBqU9T396Pz5xR"
                +
                "S053wWOZOzGRwpICKMfPOT/EpwMdQl1N/QRCvsNzYpBVzdPfohhR5v6DyRkU5"
                +
                "sfPSxzqjoyPXPPvuNmxz7xjXk/rYYkYkCd9srZuYoeQ+9kjMxczDIr7EhyfP"
                +
                "jPjUgmagq0V6YEgSSFJqIJA4v5CuEXjlgkPfET6TJFKs1Ar0Dg6xT5J+HxAw"
                +
                "1rh18JTExJWfr1tfGQKz+0jh/bpsRef/ZX13o/vqL//lNNejLpclsZyQhKSJ9"
                +
                "dt0PxBdC/iCWGLJG5mBM+zvra3Dt0hm0NzRB84nmQk0+v6kmIwut9iB9ZjtZz"
                +
                "509e/Y3s7OzmRbffgtlv7c/X5/52D8+J/nNk7/d+sp3PAiso4D5fUVZRg0MF2d"
                +
                "SJPXzs1NzdRliu85ieex1nG0cQGfU1/hFyzyUumg+fiM5khAf91Z7h+utGTOm63"
                +
                "nTX0iOPv1mo/1rVuE7u7ZscfRpA2v8qqGQIp5SWV6eo1YcddyfXBJVwOMbgwW9"
                +
                "pS2Wnh/WrqksWH5/KQ8nTtEE9z399NN/l388+38dMwwAHCaN9wAAAABJRU5E"
                + "rkJggg=="
                :
                "data:image/png;base64,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";

        AuthenticationResponse authenticationResponse =
                new AuthenticationResponse(token, "Refresh", profilePicture);
        return ResponseEntity.ok().body(authenticationResponse);
    }

    /**
     * logout an user.
     * @return void
     */
    @Operation(summary = "logout current user",
            security = @SecurityRequirement(name = "bearerAuth"))
    @PostMapping("/logout")
    public ResponseEntity<Void> logout() {
        return ResponseEntity.noContent().build();
    }

    /**
     * get the user details from the principal.
     * @param principal
     * @return userdetails
     */
    @Operation(summary = "Get logged in user profile",
            security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/me")
    public ResponseEntity<UserDetails> me(final Principal principal) {
        return ResponseEntity.ok(userDetailsService
                .loadUserByUsername(principal.getName()));
    }
}
