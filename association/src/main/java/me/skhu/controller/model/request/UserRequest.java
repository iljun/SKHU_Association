package me.skhu.controller.model.request;

import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * Created by Manki Kim on 2017-01-18.
 */
@Data
public class UserRequest {

    private int id;
    @NotNull
    private String login_id;
    @NotNull
    private String password;
    @NotNull
    private String user_name;
}
