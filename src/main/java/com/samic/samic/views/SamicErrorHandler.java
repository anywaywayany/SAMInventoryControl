package com.samic.samic.views;

import com.samic.samic.components.UIFactory;
import com.samic.samic.exceptions.SamicException;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.ErrorParameter;
import com.vaadin.flow.router.HasErrorParameter;
import com.vaadin.flow.router.ParentLayout;
import com.vaadin.flow.server.ErrorEvent;
import com.vaadin.flow.server.ErrorHandler;
import jakarta.annotation.security.PermitAll;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@PermitAll
@ParentLayout(MainLayout.class)
public class SamicErrorHandler extends VerticalLayout implements ErrorHandler,
    HasErrorParameter<SamicException> {

  private static final Logger logger = LoggerFactory.getLogger(SamicErrorHandler.class);

  @Override
  public void error(ErrorEvent errorEvent) {
    logger.error("Something wrong happened", errorEvent.getThrowable());
    if (UI.getCurrent() != null) {
      UI.getCurrent().access(() -> {
        UIFactory.NotificationError("An internal error has occurred." +
            "Contact support for assistance.");
      });
    }
  }

  @Override
  public int setErrorParameter(BeforeEnterEvent event,
      ErrorParameter<SamicException>
          parameter) {
    getElement().setText(parameter.getCaughtException().getMessage());
    logger.info("Error: {}", parameter.getException().getMessage());
    return HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
  }
}
