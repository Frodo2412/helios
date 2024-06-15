
#include "App.hpp"

App::App() : isRunning_(true), window_(NULL), renderer_(NULL) {}

bool App::init() {

  if (SDL_Init(SDL_INIT_EVERYTHING) < 0) {
    SDL_Log("SDL_Init failed: %s", SDL_GetError());
    return false;
  }

  window_ = SDL_CreateWindow("Helios", SDL_WINDOWPOS_CENTERED,
                             SDL_WINDOWPOS_CENTERED, 800, 600, 0);

  if (!window_) {
    SDL_Log("SDL_CreateWindow failed: %s", SDL_GetError());
    return false;
  }

  renderer_ = SDL_CreateRenderer(window_, -1, 0);

  if (!renderer_) {
    SDL_Log("SDL_CreateRenderer failed: %s", SDL_GetError());
    return false;
  }

  SDL_ShowWindow(window_);
  {
    int width, height, bbWidth, bbHeight;
    SDL_GetWindowSize(window_, &width, &height);
    SDL_GetWindowSizeInPixels(window_, &bbWidth, &bbHeight);
    SDL_Log("Window size: %d x %d", width, height);
    SDL_Log("Window size in pixels: %d x %d", bbWidth, bbHeight);
    if (width != bbWidth || height != bbHeight) {
      SDL_Log("Window size and window size in pixels are different!");
    }
  }

  return true;
}

int App::execute() {

  SDL_Event event;

  if (!init()) {
    return -1;
  }

  while (isRunning_) {

    while (SDL_PollEvent(&event) != 0) {
      handleEvent(event);
    }

    loop();
    render();
  }

  return 0;
}

void App::handleEvent(SDL_Event event) {

  if (event.type == SDL_EVENT_QUIT) {
    isRunning_ = false;
  }
}

void App::loop() {}

void App::render() {

  SDL_SetRenderDrawColor(renderer_, 255, 255, 255, 255);
  SDL_RenderClear(renderer_);

  SDL_RenderPresent(renderer_);
}

void App::exit() {

  SDL_DestroyRenderer(renderer_);
  SDL_DestroyWindow(window_);
  window_ = nullptr;
  renderer_ = nullptr;
  SDL_Quit();
}
