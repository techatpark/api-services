package com.gurukulams.core.service;

import com.gurukulams.core.model.ChatMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ChatMessageService {
    /**
     * Logger Facade.
     */
    private final Logger logger =
            LoggerFactory.getLogger(ChatMessageService.class);

    /**
     * this is the connection for the database.
     */
    private final DataSource dataSource;
    /**
     * this helps to execute sql queries.
     */
    private final JdbcTemplate jdbcTemplate;

    /**
     * this is the constructor.
     *
     * @param anJdbcTemplate
     * @param anDataSource
     */
    public ChatMessageService(final DataSource anDataSource,
                              final JdbcTemplate anJdbcTemplate) {
        this.dataSource = anDataSource;
        this.jdbcTemplate = anJdbcTemplate;
    }

    /**
     * Maps the data from and to the database.
     *
     * @param rs
     * @param rowNum
     * @return p
     * @throws SQLException
     */
    private ChatMessage rowMapper(final ResultSet rs,
                                  final Integer rowNum)
            throws SQLException {

        ChatMessage chatMessage = new ChatMessage((long) rs.getInt("id"),
                (long) rs.getInt("chat_id"),
                (long) rs.getInt("sender_id"),
                (long) rs.getInt("recipient_id"),
                rs.getString("sender_name"),
                rs.getString("recipient_name"),
                rs.getString("message"),
                rs.getObject("time", LocalDateTime.class)
        );
        return chatMessage;
    }

    /**
     * @param userName
     * @param chatMessage
     * @return learner
     */
    public ChatMessage create(final String userName,
                              final ChatMessage chatMessage) {
        final SimpleJdbcInsert insert = new SimpleJdbcInsert(dataSource)
                .withTableName("chat_messages")
                .usingGeneratedKeyColumns("id")
                .usingColumns("chat_id", "sender_id", "recipient_id",
                        "sender_name", "recipient_name", "message");
        final Map<String, Object> valueMap = new HashMap<>();
        valueMap.put("chat_id", chatMessage.chatId());
        valueMap.put("sender_id", chatMessage.senderId());
        valueMap.put("recipient_id", chatMessage.recipientId());
        valueMap.put("sender_name", chatMessage.senderName());
        valueMap.put("recipient_name", chatMessage.recipientName());
        valueMap.put("message", chatMessage.message());

        final Number messageId = insert.executeAndReturnKey(valueMap);
        final Optional<ChatMessage> createdLearner = read(userName,
                messageId.longValue());
        logger.info("Created learner {}", messageId);
        return createdLearner.get();
    }

    /**
     * @param userName
     * @param id
     * @return chatMessage
     */
    public Optional<ChatMessage> read(final String userName,
                                       final Long id) {
        final String query = "SELECT id, chat_id, sender_id, recipient_id, "
                + "sender_name, recipient_name, message, time FROM "
                + "chat_messages WHERE id = ?";

        try {
            final ChatMessage p = jdbcTemplate.queryForObject(query,
                    new Object[]{id}, this::rowMapper);
            return Optional.of(p);
        } catch (final EmptyResultDataAccessException e) {
            return Optional.empty();
        }

    }

    /**
     * @param id
     * @param userName the userName
     * @return chat_message
     */
    public Boolean delete(final String userName,
                          final Long id) {
        final String query = "DELETE FROM chat_messages WHERE id = ?";
        final Integer updatedRows = jdbcTemplate.update(query, id);
        return !(updatedRows == 0);
    }

    /**
     * @param userName
     * @return chat_message
     */
    public List<ChatMessage> list(final String userName) {
        final String query =
                "SELECT id, chat_id, sender_id, recipient_id, sender_name,"
                        + " recipient_name, message, time FROM chat_messages";
        return jdbcTemplate.query(query, this::rowMapper);
    }

    /**
     * @return chat_message
     */
    public Integer deleteAll() {
        final String query = "DELETE FROM chat_messages";
        return jdbcTemplate.update(query);
    }

}
