import React from "react"

export default class OlderESConstructorSyntax extends React.Component {

  /*
  If you can't use class fields in your class components
  for some reason, then you'll need to make use of the
  class `constructor` method to initialize your state object.
  The first line of the constructor method should be a call
  to `super()`, and then you can add your state variable as a
  property attached to `this`
  */

  constructor(props) {
    super(props)
    this.state = {
       goOut: "Yes"
    }
    this.toggleGoOut = this.toggleGoOut.bind(this)
  }

  /*
  If you can't use arrow functions for your class methods,
  you'll need to make sure to `bind` them inside the
  constructor above.
  */

  toggleGoOut() {
    this.setState(prevState => {
      return {
         goOut: prevState.goOut === "Yes" ? "No" : "Yes"
      }
    })
  }

  render() {
    return (
      <div className="state">
        <h1 className="state--title">Should I go out tonight?</h1>
        <div className="state--value" onClick={this.toggleGoOut}>
           <h1>{this.state.goOut}</h1>
        </div>
      </div>
    )
  }
}
