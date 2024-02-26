package com.samic.samic.views;

import com.samic.samic.components.UIFactory;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.router.ParentLayout;
import com.vaadin.flow.server.ErrorEvent;
import com.vaadin.flow.server.ErrorHandler;
import jakarta.annotation.security.PermitAll;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@PermitAll
@ParentLayout(MainLayout.class)
public class SamicErrorHandler implements ErrorHandler {

  private static final Logger logger = LoggerFactory.getLogger(SamicErrorHandler.class);

  @Override
  public void error(ErrorEvent errorEvent) {
    logger.error("Something wrong happened", errorEvent.getThrowable());
    if (UI.getCurrent() != null) {
      Notification notification = UIFactory.notificationError(
          errorEvent.getThrowable().getMessage());
      UI.getCurrent().add(notification);
      notification.open();
    }
  }
}
