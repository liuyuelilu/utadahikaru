package com.heroku.controller;

import com.heroku.entity.UserMasterEntity;
import com.heroku.form.LoginForm;
import com.heroku.mapper.UserMasterMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class UserVaildContreller {

    @Autowired
    private UserMasterMapper userMapper;

    @RequestMapping("/login")
    @ResponseBody
    Map<String, Object> login(@RequestBody LoginForm form) {

        System.out.println(form.getInputEmail());

        System.out.println(form.getInputPassword());

        UserMasterEntity user = userMapper.getOne(form.getInputEmail());


        Map<String, Object> result = new HashMap();

        if(user != null && StringUtils.equals(user.getPasswd(), form.getInputPassword()))
        {
            result.put("flag", "OK");
        }
        else
        {
            result.put("flag", "NG");
        }

        return result;
//        try (Connection connection = dataSource.getConnection()) {
//            Statement stmt = connection.createStatement();
//
//            StringBuilder sb = new StringBuilder();
//            sb.append(" SELECT ");
//            sb.append(" * ");
//            sb.append(" FROM ");
//            sb.append(" user_master ");
//            sb.append(" WHERE  ");
//            sb.append(" username = '" + form.getInputEmail() + "'");
//            sb.append(" AND passwd = '" + form.getInputPassword() + "'");
//            ResultSet rs = stmt.executeQuery(sb.toString());
//
//            ArrayList<String> output = new ArrayList<String>();
//            if (rs.next()) {
//                //
//                result.put("flag", "OK");
//            } else {
//                result.put("flag", "NG");
//            }
//            return result;
//        } catch (Exception e) {
//            result.put("flag", "ERR");
//            return result;
//        }
    }


}
