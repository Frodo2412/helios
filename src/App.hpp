#ifndef HELIOS_APP_HPP
#define HELIOS_APP_HPP

#include <SDL2/SDL.h>

class App {

  bool isRunning_;
  SDL_Window *window_;
  SDL_Renderer *renderer_;

public:
  App();

  bool init();
  int execute();
  void handleEvent(SDL_Event event);
  void loop();
  void render();
  void exit();
};

#endif // HELIOS_APP_HPP
