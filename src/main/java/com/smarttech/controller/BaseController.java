package com.smarttech.controller;

import com.smarttech.constant.AppConstant;
import com.smarttech.dto.page.PageResponse;
import org.springframework.ui.Model;

public class BaseController {

    protected static final String CREATION_MODEL_ATTRIBUTE = "createRequest";
    protected static final String SEARCH_MODEL_ATTRIBUTE = "searchRequest";

    public final void addPagingResult(Model model, PageResponse<?> pageResponse) {
        model.addAttribute(AppConstant.ResponseKey.PAGING, pageResponse);
    }

    public final void addJavascript(Model model, String...js) {
        model.addAttribute(AppConstant.ResponseKey.JS, js);
    }

    public final void addCss(Model model, String...css) {
        model.addAttribute(AppConstant.ResponseKey.CSS, css);
    }
}
