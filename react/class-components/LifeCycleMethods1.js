import React from "react"

export default class LifeCycleMethods1 extends React.Component {
  state = {
     character: {}
  }

  componentDidMount() {
    console.log("componentDidMount")
    fetch("https://swapi.dev/api/people/1")
      .then(res => res.json())
      .then(data => {
          this.setState({character: data})
      })
  }

  render() {
    console.log("render")
    return (
       <h1>{this.state.character.name}</h1>
    )
  }
}
