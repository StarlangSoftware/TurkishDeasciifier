# Deasciifier
For Developers
============

## Requirements

* [Java Development Kit 8 or higher](#java), Open JDK or Oracle JDK
* [Maven](#maven)
* [Git](#git)

### Java 

To check if you have a compatible version of Java installed, use the following command:

    java -version
    
If you don't have a compatible version, you can download either [Oracle JDK](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) or [OpenJDK](https://openjdk.java.net/install/)    

### Maven
To check if you have Maven installed, use the following command:

    mvn --version
    
To install Maven, you can follow the instructions [here](https://maven.apache.org/install.html).     

### Git

Install the [latest version of Git](https://git-scm.com/book/en/v2/Getting-Started-Installing-Git).

## Download Code

In order to work on code, create a fork from GitHub page. 
Use Git for cloning the code to your local or below line for Ubuntu:

	git clone <your-fork-git-link>

A directory called Deasciifier will be created. Or you can use below link for exploring the code:

	git clone https://github.com/olcaytaner/Deasciifier.git

## Open project with IntelliJ IDEA

Steps for opening the cloned project:

* Start IDE
* Select **File | Open** from main menu
* Choose `Deasciifier/pom.xml` file
* Select open as project option
* Couple of seconds, dependencies with Maven will be downloaded. 


## Compile

**From IDE**

After being done with the downloading and Maven indexing, select **Build Project** option from **Build** menu. After compilation process, user can run `Deasciifier`.

**From Console**

Use below line to generate jar file:

     mvn install


------------------------------------------------

Deasciifier
============
+ [Maven Usage](#maven-usage)
+ [Asciifier](#using-asciifier)
+ [Deasciifier](#using-deasciifier)


### Maven Usage

    <groupId>NlpToolkit</groupId>
    <artifactId>Deasciifier</artifactId>
    <version>1.0.1</version>
    <properties>
        <maven.compiler.source>1.8</maven.compiler.source>
        <maven.compiler.target>1.8</maven.compiler.target>
    </properties>
    <repositories>
        <repository>
            <id>NlpToolkit</id>
            <url>http://haydut.isikun.edu.tr:8081/artifactory/NlpToolkit</url>
        </repository>
    </repositories>
    <dependencies>
        <dependency>
            <groupId>NlpToolkit</groupId>
            <artifactId>Corpus</artifactId>
            <version>1.0.0</version>
        </dependency>
        <dependency>
            <groupId>NlpToolkit</groupId>
            <artifactId>Dictionary</artifactId>
            <version>1.0.2</version>
        </dependency>
        <dependency>
            <groupId>NlpToolkit</groupId>
            <artifactId>MorphologicalAnalysis</artifactId>
            <version>1.0.2</version>
        </dependency>
        <dependency>
            <groupId>NlpToolkit</groupId>
            <artifactId>NGram</artifactId>
            <version>1.0.0</version>
        </dependency>
    </dependencies>
## Using Asciifier

Asciifier converts text to a format containing only ASCII letters. This can be instantiated and used as follows:

      Asciifier asciifier = new SimpleAsciifier();
      Sentence sentence = new Sentence("çocuk"");
      Sentence asciified = asciifier.asciify(sentence);
      System.out.println(asciified);

Output:
    
    cocuk      

## Using Deasciifier

Deasciifier converts text written with only ASCII letters to its correct form using corresponding letters in Turkish alphabet. There are two types of `Deasciifier`:


* `SimpleDeasciifier`

    The instantiation can be done as follows:  
    
        FsmMorphologicalAnalyzer fsm = new FsmMorphologicalAnalyzer();
        Deasciifier deasciifier = new SimpleDeasciifier(fsm);
     
* `NGramDeasciifier`
    
    * To create an instance of this, both a `FsmMorphologicalAnalyzer` and a `NGram` is required. 
    
    * `FsmMorphologicalAnalyzer` can be instantiated as follows:
        
            FsmMorphologicalAnalyzer fsm = new FsmMorphologicalAnalyzer();
    
    * `NGram` can be either trained from scratch or loaded from an existing model.
        
        * Training from scratch:
                
                Corpus corpus = new Corpus("corpus.txt"); 
                NGram ngram = new NGram(corpus.getAllWordsAsArrayList(), 1);
                ngram.calculateNGramProbabilities(new LaplaceSmoothing());
                
        *There are many smoothing methods available. For other smoothing methods, check [here](https://github.com/olcaytaner/NGram).*       
        * Loading from an existing model:
     
                try {
                    FileInputStream inFile = new FileInputStream("ngram.model");  
                    ObjectInputStream inObject = new ObjectInputStream(inFile);
                    NGram ngram = (NGram<Word>) inObject.readObject();
                }catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
         *For further details, please check [here](https://github.com/olcaytaner/NGram).*        
            
    * Afterwards, `NGramDeasciifier` can be created as below:
        
            Deasciifier deasciifier = new NGramDeasciifier(fsm, ngram);
     
A text can be deasciified as follows:
     
    Sentence sentence = new Sentence("cocuk");
    Sentence deasciified = deasciifier.deasciify(sentence);
    System.out.println(deasciified);
    
Output:

    çocuk

        
