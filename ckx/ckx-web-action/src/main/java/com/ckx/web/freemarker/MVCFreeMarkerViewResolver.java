package com.ckx.web.freemarker;

import org.springframework.web.servlet.view.AbstractTemplateViewResolver;
import org.springframework.web.servlet.view.AbstractUrlBasedView;

public class MVCFreeMarkerViewResolver extends AbstractTemplateViewResolver {

    public MVCFreeMarkerViewResolver() {
        setViewClass(MVCFreeMarckerView.class);
    }

    @Override
    protected AbstractUrlBasedView buildView(String viewName) throws Exception {
        AbstractUrlBasedView view = super.buildView(viewName);
        if (viewName.startsWith("/")) {
            view.setUrl(viewName + getSuffix());
        }
        return view;
    }
}
