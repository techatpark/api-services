package com.example.demo.sql.controller.payload;

import org.springframework.web.multipart.MultipartFile;

public class CreateExamRequest {

    /**
     * exam to be created.
     */
    private String exam;

    /**
     * db scripts.
     */
    private MultipartFile[] scripts;

    
    /** 
     * @return Exam
     */
    public String getExam() {
        return exam;
    }

    
    /** 
     * @param exam
     */
    public void setExam(final String exam) {
        this.exam = exam;
    }

    /**
     * @return MultipartFile[]
     */
    public MultipartFile[] getScripts() {
        return scripts;
    }

    /**
     * @param scripts
     */
    public void setScripts(final MultipartFile[] scripts) {
        this.scripts = scripts;
    }

    
}
