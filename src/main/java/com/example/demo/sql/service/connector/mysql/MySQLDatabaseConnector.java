package com.example.demo.sql.service.connector.mysql;

import java.nio.file.Path;

import com.example.demo.sql.model.Exam;
import com.example.demo.sql.model.Question;
import com.example.demo.sql.service.connector.DatabaseConnector;

public class MySQLDatabaseConnector implements DatabaseConnector {

    @Override
    public final Boolean verify(final Exam exam, final Question question, final String sqlAnswer) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public final Boolean loadScript(final Exam exam, final Path[] scriptFiles) {
        // TODO Auto-generated method stub
        return null;
    }

}
