package com.techatpark.gurukulam.sql.controller.payload;

import com.techatpark.gurukulam.sql.model.Exam;

import org.springframework.web.multipart.MultipartFile;

public class CreateExamRequest {

    /**
     * exam to be created.
     */
    private Exam exam;

    /**
     * db scripts.
     */
    private MultipartFile[] scripts;

    
    /** 
     * @return Exam
     */
    public Exam getExam() {
        return exam;
    }

    
    /** 
     * @param exam
     */
    public void setExam(final Exam exam) {
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
