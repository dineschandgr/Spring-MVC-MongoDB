package com.springboot.SpringBoot.Util;

import com.springboot.SpringBoot.model.Employee;
import com.springboot.SpringBoot.service.SequenceGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;


@Component
public class UserModelListener extends AbstractMongoEventListener<Employee> {

    private SequenceGenerator sequenceGenerator;

    @Autowired
    public UserModelListener(SequenceGenerator sequenceGenerator) {
        this.sequenceGenerator = sequenceGenerator;
    }

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Employee> event) {
        if (event.getSource().getId() < 1) {
            event.getSource().setId(sequenceGenerator.generateSequence(Employee.SEQUENCE_NAME));
        }
    }
}
