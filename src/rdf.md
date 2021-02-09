# RDF cheats

## RDF = Resource Description Framework

The purpose of RDF is to provide a structure (aka framework) for describing identified thinks (aka resources).

In order to make meaningful statements about things, the things you're talking about have to be identified. URIs (uniform resource identifiers) tend to be used for this purpose. These look like URLs, but they may not represent an actual web page.

```
http://www.foaf.com/Person#WillSmith
http://www.allmovie.com/Actor#WillSmith
```

RDF has three basic elements:

- **Resources**: the things being described
- **Properties**: the relationships between things
- **Classes**: the buckets used to group things

These elements are combined to make simple statements in the form of "triples":

```
<subject> <predicate> <object>
```

Examples:

```
<http://www.w3.org/2001/sw/RDFCore/ntriples/> <dc:creator> "Dave Beckett"
<http://www.w3.org/2001/sw/RDFCore/ntriples/> <dc:creator> "Art Barstow"
<http://www.w3.org/2001/sw/RDFCore/ntriples/> <dc:publisher> <http://www.w3.org/>
```

Expressed as XML:

```xml
<rdf:RDF xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#"
  xmlns:dc="http://purl.org/dc/elements/1.1/">
  <rdf:Description rdf:about="http://www.w3.org/2001/sw/RDFCore/ntriples/">
    <dc:creator>Dave Beckett</dc:creator>
    <dc:creator>Art Barstow</dc:creator>
    <dc:publisher rdf:resource="http://www.w3.org/" />
  </rdf:Description>
</rdf:RDF>
```

#### RDF properties

- `type`
- `subClassOf`
- `subPropertyOf`
- `range`
- `domain`
- `label`
- `comment`

The most important RDF property is `rdf:type`. This declares that a resource belongs to a certain class, which in turn defines which properties will be relevant to that resource.

```
<WillSmith> <type> <Actor>
```

The `subClassOf` property add inheritance to a class. Example:

```
<Actor> <subClassOf> <Person>
```

This means that all members of the Actor class are also members of the Person class. All properties are inherited, and new properties specific to Actor can be added.

```
<WillSmith> <type> <Actor>
```

implies:

```
<WillSmith> <type> <Person>
```

Individual properties can also extend from other properties using `subPropertyOf`.

```
<hasStar> <subPropertyOf> <hasActor>
```

This means that, if you make a statement using the `hasStar` property, a more general statement using the `hasActor` property is also true.

```
<MenInBlack> <hasStar> <WillSmith>
```

implies:

```
<MenInBlack> <hasActor> <WillSmith>
```

The `range` and `domain` properties add constraints. Example:

```
<hasStar> <range> <Actor>
<hasStar> <domain> <Movie>
```

This means that, if you make a statement using the `hasStar` property, the system will assume the subject is a `Movie` and the object is an `Actor`

```
<MenInBlack> <hasStar> <WillSmith>
```

implies:

```
<MenInBlack> <type> <Movie>
<WillSmith> <type> <Actor>
```

A `label` is a human-readable label for a resource, while `comment` is a human-readable description.

## OWL = Web Ontology Language

OWL extends RDF, adding more vocabulary for the purpose of allowing the development of ontologies (ie the definition and classification of concepts and entities, and the relationships between them):

- **Relationships** between classes, eg:

  - `disjointWith` means resources belonging to one class cannot belong to the other.
  
    ```
    <Person> <disjointWith> <Country>
    ```

  - `complementOf` means the members of one class are _all_ the resources that do not belong to the other.

    ```
    <InanimateThings> <complementOf> <LivingThings>
    ```
- **Equality**, eg:

  - `sameAs` indicates that two resources actually refer to the same real-world thing or concept. This can be used to define aliases.
  
  - `equivalentClass` indicates that two classes have the same set of members (but do not represent the same thing).

- **Richer properties**, eg:
  
  - `symmetrical`: a relationship between A and B is also true between B and A.

    ```
    <WillSmith> <marriedTo> <JadaPinkettSmith>
    ```

    implies

    ```
    <JadaPinkettSmith> <marriedTo> <WillSmith>
    ```

  - `transitive`: a relationship between A and B and between B and C is also true between A and C.

    ```
    <piston> <isPartOf> <engine>
    <engine> <isPartOf> <automobile>
    ```

    implies

    ```
    <piston> <isPartOf> <automobile>
    ```

  - `inverseOf`: a relationship of type X between A and B implies a relationship of type Y between B and A.

    ```
    <starsIn> <inverseOf> <hasStar>
    <MenInBlack> <hasStar> <WillSmith>
    ```

    implies

    ```
    <WillSmith> <starsIn> <MenInBlack>
    ```

- **Class property restrictions**, to define the members of a class based on their properties eg:

  - `allValuesFrom`: resources with properties that _only_ have values that meet this criteria. 

    ```
    <hasParents> <allValuesFrom> <Human>
    ```

    A resource that has the property `hasParents` can be defined as also being members of the `Human` class.

  - `someValuesFrom`: resources with properties that have _at least one_ value that meet criteria.

    ```
    <hasGraduated> <someValuesFrom> <College>
    ```

    Resources that meet this criteria can be defined as being members of the `CollegeGraduates` class.

This added vocabulary allows systems to make more sense of first-order logic, eg:

- All men are mortal
- Socrates is a man
- Therefore Socrates is mortal

Systems can infer new triples based on existing ones, and deduce new facts based on the stated facts.

```
<piston> <isPartOf> <engine>
<engine> <isPartOf> <automobile>
```

implies

```
<piston> <isPartOf> <automobile>
```
