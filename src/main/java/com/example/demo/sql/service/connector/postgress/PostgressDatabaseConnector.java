package com.example.demo.sql.service.connector.postgress;

import java.io.File;

import com.example.demo.sql.model.Exam;
import com.example.demo.sql.model.Question;
import com.example.demo.sql.service.connector.DatabaseConnector;

public class PostgressDatabaseConnector implements DatabaseConnector {

    @Override
    public final Boolean verify(final Exam exam, final Question question, final String sqlAnswer) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public final Boolean loadScript(final Exam exam, final File[] scriptFiles) {
        // TODO Auto-generated method stub
        return null;
    }

}
