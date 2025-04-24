/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.project.csc212;

/**
 *
 * @author alanoud
 */
public class Test {
public static void main(String[] args) {
    
PhotoManager manager = new PhotoManager();
 InvIndexPhotoManager invmanager = new InvIndexPhotoManager();
 // Create photo with tags 
Photo photo1 = new Photo("hedgehog.jpg",toTagsLinkedList("animal, hedgehog, apple, grass, green"));
Photo photo2 = new Photo("bear.jpg",toTagsLinkedList("animal, bear, cab, grass,wind"));
Photo photo3 = new Photo("orange-butterfly.jpg",toTagsLinkedList("insect,butterfly, flower, color"));

// Add photos to manager
    manager.addPhoto(photo1);
    manager.addPhoto(photo2);
    manager.addPhoto(photo3);
    
 // Add photos to invmanager
    invmanager.addPhoto(photo1);
    invmanager.addPhoto(photo2);
    invmanager.addPhoto(photo3);
    

// Create albums
     Album album1 = new Album("Album1", "bear", manager, invmanager);
     Album album2 = new Album("Album2", "animal AND grass", manager, invmanager);
     Album album3 = new Album("Album3", "", manager, invmanager);
        
// Print paths and tags of a photo
System.out.println("Get photo1 path and tags:");
System.out.println("\nphoto1 path: " + photo1.getPath());
System.out.print("Photo1 Tags: ");
LinkedList<String> tags = photo1.getTags();
displayTags(tags);

  

  // === Album 1 Details ===
        System.out.println("\n\n[ Album 1 Info ]");
        System.out.println("Name       : " + album1.getName());
        System.out.println("Condition  : " + album1.getCondition());
        LinkedList<Photo> photos = album1.getPhotos();
        System.out.println(album1.getCondition());
        System.out.println("Photos     :");
        showPhotoPaths(photos);
        System.out.printf("Comparisons made for condition \"%s\": %d\n", album1.getCondition(), album1.getNbComps());

     // === Album 2 Details ===
        System.out.println("\n\n[ Album 2 Info ]");
        System.out.println("Name       : " + album2.getName());
        System.out.println("Condition  : " + album2.getCondition());
        photos = album2.getPhotos();
        System.out.println(album2.getCondition());
        System.out.println("Photos     :");
        showPhotoPaths(photos);
        System.out.printf("Comparisons made for condition \"%s\": %d\n", album2.getCondition(), album2.getNbComps());
      
      // === Album 3 Details ===
        System.out.println("\n\n[ Album 3 Info ]");
        System.out.println("Name       : " + album3.getName());
        System.out.println("Condition  : " + album3.getCondition());
        photos = album3.getPhotos();
        System.out.println(album3.getCondition());
        System.out.println("Photos     :");
        showPhotoPaths(photos);
        System.out.printf("Comparisons made for condition \"%s\": %d\n", album3.getCondition(), album3.getNbComps());


        // Delete one photo and test result
        System.out.println("\nDelete the photo ’bear.jpg’:");
        manager.deletePhoto("bear.jpg");

//      Print remaining photos
        System.out.println("\nRemaining photos after deletion:");
        showPhotoPaths(manager.getPhotos());

        
        // === Album 3 After Deletion ===
        System.out.println("\n\n[ Album 3 After Deletion ]");
        System.out.println("Name       : " + album3.getName());
        System.out.println("Condition  : " + album3.getCondition());
        //You can get the list of photos in album2 by calling album2.getPhotos().
        photos = album3.getPhotos();
        //You can write a method that prints the list of photos in album2.
        System.out.println(album3.getCondition());
        System.out.println("Photos     :");
        showPhotoPaths(photos);
        System.out.printf("Comparisons made for condition \"%s\": %d\n", album3.getCondition(), album3.getNbComps());


}


private static LinkedList<String> toTagsLinkedList(String tags) {
LinkedList<String> result = new LinkedList<String>();
String[] tagsArray = tags.split("\\s*,\\s*");
for (int i = 0; i < tagsArray.length; i++) {
result.insert(tagsArray[i]);
}
return result;
}


// Print all strings in a LinkedList
private static void displayTags(LinkedList<String> tagsList) {
    if (tagsList.empty()) return;

    tagsList.findFirst();
    do {
        System.out.print(tagsList.retrieve() + " ");
        if (tagsList.last()) break;
        tagsList.findNext();
    } while (true);
}


// Print paths of all photos in a LinkedList
private static void showPhotoPaths(LinkedList<Photo> photoList) {
    if (photoList.empty()) return;

    photoList.findFirst();
    do {
        System.out.println(photoList.retrieve().getPath());
        if (photoList.last()) break;
        photoList.findNext();
    } while (true);
}


}
