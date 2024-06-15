
#ifndef HELIOS_COLOR_H
#define HELIOS_COLOR_H

struct Color {
  double red, green, blue, alpha;

  Color() : red(0), green(0), blue(0), alpha(0) {}
  Color(double red, double green, double blue, double alpha = 255)
      : red(red), green(green), blue(blue), alpha(alpha) {}
};

#endif // HELIOS_COLOR_H
