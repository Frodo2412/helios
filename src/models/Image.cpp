
#include "Image.hpp"

Image::Image() : width_(0), height_(0), texture_(NULL) {}

Image::~Image() {
  if (texture_) {
    SDL_DestroyTexture(texture_);
  }
}

void Image::initialize(const int width, const int height,
                       SDL_Renderer *renderer) {
  width_ = width;
  height_ = height;
  renderer_ = renderer;

  pixels_.resize(width, std::vector<Color>(height, {0, 0, 0}));
}

void Image::setPixel(const int x, const int y, const Color color) {
  pixels_[x][y] = color;
}

void Image::display() {

  Uint32 *pixels = new Uint32[width_ * height_];

  memset(pixels, 0, width_ * height_ * sizeof(Uint32));

  for (int x = 0; x < width_; x++) {
    for (int y = 0; y < height_; y++) {
      pixels[x + y * width_] = pixels_[x][y].toUint32();
    }
  }

  SDL_UpdateTexture(texture_, NULL, pixels, width_ * sizeof(Uint32));

  delete[] pixels;

  SDL_Rect srcRect, bounds;
  srcRect.x = 0;
  srcRect.y = 0;
  srcRect.w = width_;
  srcRect.h = height_;
  bounds = srcRect;

  SDL_RenderTexture(renderer_, texture_, &srcRect, &bounds);
}

void Image::initTexture() {
  Uint32 rmask, gmask, bmask, amask;

#if SDL_BYTEORDER == SDL_BIG_ENDIAN
  rmask = 0xff000000;
  gmask = 0x00ff0000;
  bmask = 0x0000ff00;
  amask = 0x000000ff;
#else
  rmask = 0x000000ff;
  gmask = 0x0000ff00;
  bmask = 0x00ff0000;
  amask = 0xff000000;
#endif

  if (texture_ != NULL) {
    SDL_DestroyTexture(texture_);
  }

  SDL_Surface *surface =
      SDL_CreateRGBSurface(0, width_, height_, 32, rmask, gmask, bmask, amask);

  texture_ = SDL_CreateTextureFromSurface(renderer_, surface);

  SDL_FreeSurface(surface);
}
