/*
A package in Go is a bit like namespaces in PHP or modules in Ruby.
*/
package main

import (
  "fmt"
  "math/rand"
)

/*
Every Go program MUST have a main function.
Go has a convention that functions that start with lower case letters are
private, and those that start with upper case letters are public, callable
externally of the package in which they are defined. (The exception is the
main function, which is a special case as it is the program's entry point.)
The casing convention is also used for variables.
*/
func main() {
  /*
  This is the long form of declaring a variable. The type is explicit.
  */
  var greeting string = "Hello! Is it %s you're looking for?\n"

  /*
  This is the short form of declaring a variable. The type is inferred
  from the value. Here, the actual type is an array of two strings. This
  is the more common way to declare variables in Go.
  */
  words := [2]string{"me", "tea"}

  rand.Seed(42)

  for i := 0; i < 5; i++ {
    fmt.Printf(greeting, words[rand.Intn(len(words))])
  }
}
