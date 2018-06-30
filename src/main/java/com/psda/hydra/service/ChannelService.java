package com.psda.hydra.service;



import com.psda.hydra.model.Channel;
import com.psda.hydra.payload.ChannelDTO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.provider.HibernateUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import java.util.*;


@Service
@Transactional
public class ChannelService {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    private EntityManager em;





}
