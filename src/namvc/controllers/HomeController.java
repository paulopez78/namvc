package namvc.controllers;

import namvc.views.HomeView;

public class HomeController extends NaMvcController{
  public HomeController()
  {
    this.View = new HomeView();
  }
}
