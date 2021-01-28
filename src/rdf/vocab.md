# RDF vocabulary

#### Classes

- `rdf:XMLLiteral`: the class of XML literal values
- `rdf:Property`: the class of properties
- `rdf:Statement`: the class of RDF statements
- `rdf:Alt`, `rdf:Bag`, `rdf:Seq`: containers of alternatives, unordered containers, and ordered containers (`rdf:Container` is a super-class of the three)
- `rdf:List`: the class of RDF lists
- `rdf:nil`: an instance of `rdf:LIst` representing the empty list

#### Properties

- `rdf:type`: an instance of `rdf:Property` used to state that a resource is an instance of a class
- `rdf:first`: the first item in the subject RDF list
- `rdf:rest`: the rest of the subject RDF list after `rdf:first`
- `rdf:value`: idiomatic property used for structured values
- `rdf:subject`: the subject of the RDF statement
- `rdf:predicate`: the predicate of the RDF statement
- `rdf:object`: the object of the RDF statement
