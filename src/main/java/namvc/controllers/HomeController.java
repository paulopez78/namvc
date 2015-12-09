package main.java.namvc.controllers;

import main.java.namvc.framework.NaMvcController;
import main.java.namvc.views.HomeView;

public class HomeController extends NaMvcController {
  public HomeController()
  {
    this.View = new HomeView();
  }
}
