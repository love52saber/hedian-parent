package com.hedian.controller;


import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author why
 * @since 2018-05-03
 */
@RestController
@RequestMapping("/userToRole")
//不加入swagger ui里
@ApiIgnore
public class UserToRoleController {

}

