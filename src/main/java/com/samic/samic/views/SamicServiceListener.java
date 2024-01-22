package com.samic.samic.views;

import com.vaadin.flow.server.ServiceInitEvent;
import com.vaadin.flow.server.VaadinServiceInitListener;
import com.vaadin.flow.server.VaadinSession;

public class SamicServiceListener implements VaadinServiceInitListener {

  @Override
  public void serviceInit(ServiceInitEvent event) {
    event.addIndexHtmlRequestListener(response -> {
      // IndexHtmlRequestListener to change the bootstrap page
    });

    event.addDependencyFilter((dependencies, filterContext) -> {
      // DependencyFilter to add/remove/change dependencies sent to
      // the client
      return dependencies;
    });

    event.addRequestHandler((session, request, response) -> {
      // RequestHandler to change how responses are handled
      return false;
    });

    VaadinSession.getCurrent().setErrorHandler(new SamicErrorHandler());
  }

}
