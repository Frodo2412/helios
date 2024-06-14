cmake_minimum_required(VERSION 3.28)
project(helios)

set(CMAKE_CXX_STANDARD 17)

add_subdirectory(SDL)

add_executable(helios main.cpp
        src/App.cpp
        src/App.h)

target_link_libraries(helios SDL3::SDL3)
