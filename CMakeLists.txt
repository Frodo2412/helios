cmake_minimum_required(VERSION 3.28)
project(helios)

set(CMAKE_CXX_STANDARD 17)

add_subdirectory(SDL)
add_subdirectory(aristotle)

add_executable(helios main.cpp
        src/App.cpp
        src/App.hpp
        src/models/Image.cpp
        src/models/Image.hpp
        src/models/Color.cpp
        src/models/Color.h
        src/models/Pixel.cpp
        src/models/Pixel.hpp)

target_link_libraries(helios SDL2::SDL2)
target_link_libraries(helios aristotle)
