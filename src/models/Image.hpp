#ifndef HELIOS_IMAGE_HPP
#define HELIOS_IMAGE_HPP

#include "Color.h"
#include <SDL2/SDL.h>
#include <vector>

class Image {

  int width_, height_;

  SDL_Renderer *renderer_;
  SDL_Texture *texture_;

  std::vector<std::vector<Color>> pixels_;

public:
  Image();
  ~Image();

  void initialize(const int width, const int height, SDL_Renderer *renderer);

  void setPixel(const int x, const int y, const Color color);

  void display();
  void initTexture();
};

#endif // HELIOS_IMAGE_HPP
