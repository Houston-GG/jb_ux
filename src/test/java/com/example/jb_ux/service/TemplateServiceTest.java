package com.example.jb_ux.service;

import com.example.jb_ux.exception.TemplateValuesException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TemplateServiceTest {

    @Autowired
    TemplateService templateService;

    private static final Map<String, String> values = new HashMap<>();

    @BeforeClass
    public static void prepare(){
        values.put("name", "Jet");
        values.put("age", "69");
    }

    @Test
    public void processTemplate_success(){
        String templateString = "$name$ is $age$ years old.";
        Assert.assertEquals("Jet is 69 years old.", templateService.processTemplate(templateString, values));
    }

    @Test
    public void processTemplate_hasNotSurroundedKeys(){
        String templateString = "name $name$ is age $age$ years old.";
        Assert.assertEquals("name Jet is age 69 years old.", templateService.processTemplate(templateString, values));
    }

    @Test
    public void processTemplate_noKeyInTemplate(){
        String templateString = "Hello world.";
        Assert.assertEquals("Hello world.", templateService.processTemplate(templateString, values));
    }

    @Test
    public void processTemplate_emptyString(){
        String templateString = "";
        Assert.assertEquals("", templateService.processTemplate(templateString, values));
    }

    @Test(expected = TemplateValuesException.class)
    public void processTemplate_notEnoughValuesProvided(){
        String templateString = "name $name$ is age $age$ years old. $loss$";
        templateService.processTemplate(templateString, values);
    }

    @Test
    public void processTemplate_dollars(){
        String templateString = "$$$ $name$ is $age$ years old. $$$";
        Assert.assertEquals("$$$ Jet is 69 years old. $$$", templateService.processTemplate(templateString, values));
    }
}
