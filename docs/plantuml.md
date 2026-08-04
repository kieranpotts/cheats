# PlantUML rendering

Some cheats embed PlantUML notation.

To render PlantUML diagrams in AsciiDoc, you need a PlantUML server running. It
is RECOMMENDED to use [Kroki](https://kroki.io/), a free web service that
renders diagrams and charts from text representations, not only in the PlantUML
DSL but also [Mermaid](https://mermaid.js.org/), [Graphviz](https://graphviz.org/),
and many others.

In VS Code, add the following setting to your `settings.json` file — either at
the workspace or user level. This enables the Kroki extension for AsciiDoc and
uses the free public Kroki server.

```json
{
  "asciidoc.extensions.enableKroki": true,
  "asciidoc.preview.asciidoctorAttributes": {
    "kroki-server-url": "https://kroki.io",
  },
}
```

You can also run a local Kroki server. The full instructions are
[here](https://docs.kroki.io/kroki/setup/use-docker-or-podman/), but basically
it involves pulling and running a Docker container based on the official Kroki
image.

```sh
# If you haven't done this before, pull the Kroki server image from Docker Hub.
# https://hub.docker.com/r/yuzutech/kroki
docker pull yuzutech/kroki

# Run a container based on this image. Run the container in detached mode (-d)
# and map the container's port 8000 to your local machine's port 8088.
docker run -d -p 8088:8000 yuzutech/kroki
```

Go to http://localhost:8088/ to verify that the Kroki server is running. Then
change your `settings.json` to point to the local server instead.

```json
{
  "asciidoc.extensions.enableKroki": true,
  "asciidoc.preview.asciidoctorAttributes": {
    "kroki-server-url": "http://localhost:8088",
  },
}
```

For AsciiDoc, the PlantUML DSL code needs to be embedded within
[literal blocks](https://docs.asciidoctor.org/asciidoc/latest/verbatim/literal-blocks/).
If you have everything configured correctly, you should be able to see a
rendered diagram when viewing a `.adoc` file with PlantUML notation in preview
mode, for example:

```
[plantuml]
....
@startuml
entity person {
* id: INT <<FK>>
* name: VARCHAR(128)
---
address: VARCHAR(256)
email: VARCHAR(128)
phone: VARCHAR(16)
}
@enduml
....
```

If you can see the Kroki landing page at http://localhost:8088 in your web
browser, but the diagrams do not generate in AsciiDoc preview in VS Code, first
try restarting VS Code. If that doesn't work, try adjusting the security
settings. Open VS Code's command palette (Ctrl+Shift+P), select "AsciiDoc:
Manage Preview Security Settings", and choose "Allow insecure local content".
