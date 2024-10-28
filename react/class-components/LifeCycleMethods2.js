import React from "react"

export default class LifeCycleMethods2 extends React.Component {
  state = {
    count: 1,
    character: {}
  }

  add = () => {
    this.setState(prevState => ({count: prevState.count + 1}))
  }

  subtract = () => {
    this.setState(prevState => ({count: prevState.count - 1}))
  }

  getStarWarsCharacter = (id) => {
    fetch(`https://swapi.dev/api/people/${id}`)
      .then(res => res.json())
      .then(data => this.setState({character: data}))
  }

  componentDidMount() {
    this.getStarWarsCharacter(this.state.count)
  }

  componentDidUpdate(prevProps, prevState) {
    console.log("Updated")

    /*
    This is a common use case for `componentDidUpdate`. We often need
    to check if the component's props or state have changed before
    performing an action that would trigger further changes in state,
    so putting calls to this function in an infinite loop.
    */

    if(prevState.count !== this.state.count) {
      this.getStarWarsCharacter(this.state.count)
    }
  }

  render() {
    return (
      <>
        <div className="counter">
          <button className="counter--minus" onClick={this.subtract}>–</button>
          <div className="counter--count">
            <h1>{this.state.count}</h1>
          </div>
          <button className="counter--plus" onClick={this.add}>+</button>
        </div>
        <h1>{this.state.character.name || "Loading..."}</h1>
      </>
    )
  }
}
