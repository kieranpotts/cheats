# LaTeX cheats

LaTeX is a markup language for writing and formatting documents. It is widely used in academia due to its support for writing mathemtical formula and special symbols. Most textbooks are written in LaTeX.

To write documents in LaTeX, you will need to downlaod a LaTeX package and compiler. There are several such packages and compilers.

LaTeX documents can be written in a dedicated LaTeX IDE or any plain text editor. LaTeX documents are given the `.tex` file extension.


## Escape character

The content of LaTeX documents is peppered with LaTeX commands. All LaTeX commands are prefixed  with a backslash character: `\`. For example, to start a new paragraph, the command `par` is written as `\par`. 

LaTeX also uses a variety of special symbols such as curly braces — `{` and `}` — which encapsulate parameters for commands, the tilde character — `~` — which generates a unit of whitespace, and the percent sign — `%` — which prefixes comments. In general, to have these characters printed verbatim, they need to be escaped with the backslash character, too.


## Comments

The `%` character is used to comment-out a line.


## Formatting

#### Inline formatting

Inline formatting  commands receive the text to be formatted within curly braces immediately after the command:

- `\textb{Text to be presented bold.}`
- `\textit{Text to be presented in italics.}`
- `\underline{Text to be underlined.}`
- `\sout{Text to strike out.}` (requires the `ulem` package)
- `\textsc{Text to be written in non-shouting capital letters.}`

Inline formatting commands can be nested.

#### Block formatting

Blocks of text can be formatted using `\begin{command}` and `\end{command}`, where `command` references a formatting command. Examples commands include:

- `{flushleft}`: Left-aligns the text.
- `{center}`: Centres the text.
- `{flushright}`: Right-aligns the text.
- `{verbatim}`: Prints everything verbatim, including commands and LaTeX special characters.
- `{huge}`: Enlarges the font.
- `{small}`: Shrinks the font.

Besides formatting, `\begin` and `\end` are used for special segments, including:

- Lists. Each entry is indicated by the `\item` command. There are many types of list including:
  - `{itemize}`: Bulleted lists.
  - `{enumerate}`: Numbered lists.
  - `{description}`: Labelled lists, where each item starts with an emphasized word, followed by its description.
- `{tabular}`: Creates a table.
- `{displaymath}`: Enables "Math Mode".

In addition, LaTeX documents themselves are bounded between `\begin{document}` and `\end{document}`.


## Sections

Chapters and sections are generated with the following commands:

- `\section{title}`
- `\subsection{title}`

Sub-sections have two numbers separated by a period (`.`).


## Tables

To create a table, you will have an extra argument to give when defining\begin{tabular}{layout}. Thelayoutargument will specify how many columnsthere are, where the text is placed in each column, and if a line separates thecolumns

A layout example is `{|c|c|lr|c|c}`. This will have six columns: two centred columns, a left-aligned column followed by a right-aligned column, then two further centred columns. All columns have a line of separation except the left- and right-aligned columns in the middle, and there is no line on the right-side of the table.

Each row is separated by the standard LaTeX line breaks — `\\` — and column values are separated by ampersands `&`. To place a horizontal line of separation between rows use the `\hline` command.


## References and citations

When revising a document, it can be common to reorder sections, figures, andtables as well as edit the bibliography. LaTeX makes this easier for you by supporting the use of author-generated "labels" to create references between separate items of content.

When creating a section or caption, include the command `\label{labelName}` before ending it. To reference that sectio or caption, write `Section/Figure/Table∼ \ref{labelName}`. The compiler will fill in the appropriate name in place of the `\ref` command. The tilde (`∼`) is a special character that makes sure that the number is not separated from the word preceding it. 

Likewise, the bibtex file's names for each bibliographic entry may be cited in this way, in case the reference list changes. Simply use the command `\cite{bibitemName}`.


## Other useful commands

- `\parindent=XX` sets how much to indent paragraphs (`XX` is replaced with a floating point).
- `\parskip=XX` sets how much space to leave between consecutive paragraphs (`XX` is replaced with a floating point).
- `\tableofcontents` generates a table of contents.
- \maketitle` will generate a title section. The title is provided with the preceeding command `\title{title}`, authors with `\author{author(s)}`, and the date with `\date{date}`.
- Footnotes can be made using the command `\footnote{Text to appear in footnote}`. They are numbered in order and placed at the bottom of the page. 
- A variation of footnotes that is sometimes used for author information when generating the title section is the command `\thanks{Author information}`.


## Math mode

Anything written between two dollar signs — `$...$` — is presented in LaTeX's special "Math Mode". Text here is presented to look "mathy" and there are special commands available in this context that are not available to other parts of a LaTeX document.

The following is just a small subset of all the commands available in math mode:

- `\leq` is `≤`
- `\geq` is `≥`
- `\neq` is the "not equal" to character
- `\in` is `∈`
- `\subset` is `⊂`
- `\subseteq` is `⊆`
- `\cup` is `∪`
- `\cap` is `∩`
- `\vee` is `∨`
- `\wedge` is `∧`
- `\neg` is `¬`
- `\ldots` is `. . .`
- `\cdots` is `···`
- `\cdot` is `·`
- `\infty` is `∞`
- `\sum` is `∑`
- `\prod` is `∏`
- `\int` is `∫`
- `\partial` is `∂`
- `\mid` is `|`
- `\leftarrow` is `←`; other available arrows are `\uparrow`, `\downarrow`, `\rightarrow`, `\leftrightarrow`, etc.
- `\Leftarrow` with a capital L is a fat arrow `⇐`
- `\Omega` is `Ω and, and `\alpha` is `α`; other Greek lettering is available
- Any other text is assumed to be a string of variables. Spaces will be ignored but `\;` can be used to add extra whitespace to better separate symbols


## Packages

Standard LaTeX commands can be extended with third-party plugins, called packages. (Some packages come with the standard LaTeX installation, while others can be downloaded separately.)

To use a package in a document, use the command `\usepackage{packageName}`. This must be used after the `\documentclass` command. Some packages have special parameters that are inputted _before_ the package name.

Popular packages include:

- `\usepackage{graphicx}` allows graphics to be embedded in a LaTeX document. `\usepackage{epsfig}` allows PostScript images to be rendered from a LaTeX document.
- `\usepackage[margin=XX]{geometry}` can cheat the margins when you want to save trees or specify particular guidelines. Replace `XX` with a floating-point number followed by a unit of measurement such as `in` or `cm` (no space between the number and unit).
- `\usepackage{amsmath}`, `\usepackage{amsfonts}`, and `\usepackage{amssymb}` form the set of AMS (American Mathematics Society) packages that the TexMaker IDE always suggests. With these three packages, almost every math symbol is available.


## Compilation

LaTeX documents can be compiled using special LaTeX IDEs such as TexMaker, MikTex or TexLive. Alternatively, compilation can be done from the command line.

#### PostScript (`.eps` and/or `.ps`)

Compile the `.tex` file with `latex`. This produces a `.dvi` file. 

```
> latex myfile.tex
```

If you have referenced sections, then a second compilation will be necessary due to the one-pass compiler missing the label of later sections. There are no side effects to running the compilation step multiple times, so it's good practice to run it twice to capture all references.

```
> latex myfile.tex
```

If you also have a bibtex file to compile, do that now. The previous compilation step will have produced an auxiliary (`.aux`) file. This lists the references cited in each `.bib` file. So, to synchronise the auxiliary file with the bibtex file:

```
> bibtex myfile.aux
```

Now recompile the document, again TWICE. The first run will generate the bibliography section and a list of citation information, while the second run will fill in the `\cite` commands with that information.

```
> latex myfile.tex
> latex myfile.tex
```

Convert the `.dvi` file into a `.ps` using `dvips`.

```
dvips myfile.dvi
```

To convert `.ps` to a `.pdf`, use `ps2pdf`.

```
ps2pdf myfile.ps
```

(Note, if the bibliography changes, the auxiliary file needs to be deleted and the whole compilation process started from the beginning.)

#### PDF

To produce a PDF more directly, first compile your `.tex` file using `pdflatex`. Again, if you have referenced sections, then a second compilation step will be necessary to ensure all references are captured.

```
> pdflatest myfile.tex
> pdflatest myfile.tex
```

If you have a bibtex file to compile, synchronise those now. The previous compilation step would have produced an auxiliary file (`.aux`), which lists the references cited in each `.bib` file. To synchronise the auxiliary file with the bibtex file:

```
> bibtex myfile.aux
```

Now recompile the document, again TWICE. The first run will generate the bibliography section and a list of citation information, while the second run will fill in the `\cite` commands with that information.

```
> pdflatest myfile.tex
> pdflatest myfile.tex
```
