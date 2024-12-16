package com.ui.pojo;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Setter
@Getter
public class Config {

    private Map<String,Environment> environments;

}
