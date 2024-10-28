import React from "react"

export default class Counter extends React.Component {
  state = {
    count: 0
  }

  add = () => {
    this.setState(prevState => ({count: prevState.count + 1}))
  }

  subtract = () => {
    this.setState(prevState => ({count: prevState.count - 1}))
  }

  render() {
    return (
      <div className="counter">
        <button className="counter--minus" onClick={this.subtract}>–</button>
        <div className="counter--count">
          <h1>{this.state.count}</h1>
        </div>
        <button className="counter--plus" onClick={this.add}>+</button>
      </div>
    )
  }
}
