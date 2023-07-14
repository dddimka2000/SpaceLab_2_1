package org.example.controller.modelPage;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class MainPageModel {

    private String logUser;
    private String imagePath;
    private Boolean backgroundSelection;
    public MainPageModel() {
    }
}
