package org.example.web;

import org.example.service.MenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class MenuController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private MenuService service;
}
