package LLD.OOD.Creational.Prototype;

import java.util.ArrayList;
import java.util.List;

// Prototype interface
interface DocumentPrototype {
  DocumentPrototype cloneDocument();
  void display();
}

// Concrete Prototype
class Document implements DocumentPrototype {

  private String content;
  private List<String> images;
  private String formatting;
  private List<String> annotations;

  // Constructor
  public Document(
    String content,
    List<String> images,
    String formatting,
    List<String> annotations
  ) {
    this.content = content;
    this.images = new ArrayList<>(images); // Deep copy of images list
    this.formatting = formatting;
    this.annotations = new ArrayList<>(annotations); // Deep copy of annotations list
  }

  // Clone method
  @Override
  public DocumentPrototype cloneDocument() {
    // Deep copy of ComplexDocument
    return new Document(
      this.content,
      this.images,
      this.formatting,
      this.annotations
    );
  }

  // Display document content
  @Override
  public void display() {
    System.out.println("Content: " + content);
    System.out.println("Images: " + images);
    System.out.println("Formatting: " + formatting);
    System.out.println("Annotations: " + annotations);
  }

  // Additional methods to manipulate the document (for demonstration purposes)
  public void addImage(String image) {
    images.add(image);
  }

  public void addAnnotation(String annotation) {
    annotations.add(annotation);
  }
}

// Client
class Client {

  public static void main(String[] args) {
    List<String> images = new ArrayList<>();
    images.add("Image1.png");
    List<String> annotations = new ArrayList<>();
    annotations.add("Annotation1");

    Document originalDoc = new Document(
      "Hello, World!",
      images,
      "Basic",
      annotations
    );

    // Cloning the document using the prototype pattern
    DocumentPrototype copiedDoc = (Document) originalDoc.cloneDocument();

    // Making changes to the original document
    originalDoc.addImage("Image2.png");
    originalDoc.addAnnotation("Annotation2");

    // Displaying the contents of both documents
    System.out.println("Original Document:");
    originalDoc.display();
    System.out.println("\nCopied Document:");
    copiedDoc.display();
  }
}
