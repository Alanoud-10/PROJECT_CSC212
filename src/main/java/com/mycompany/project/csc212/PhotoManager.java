/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.project.csc212;

/**
 *
 * @author alanoud
 */
public class PhotoManager {

    // List to store all photos
    private LinkedList<Photo> managedPhotos;

    // Constructor to initialize the photo manager
    public PhotoManager() {
        managedPhotos = new LinkedList<Photo>(); // Initialize an empty list of photos
    }
    
   // Return all the photos managed by this photo manager
    public LinkedList<Photo> getPhotos() {
        return managedPhotos;
    }
    
    // Add a photo to the collection if not already added
    public void addPhoto(Photo p) {
        String newPath = p.getPath();
        if (!isPhotoExist(newPath)) {
        managedPhotos.insert(p); // Add new photo to the list
    }
    }

    // Check if a photo with the given path already exists in the collection
    private boolean isPhotoExist(String path) {
        if (managedPhotos.empty()) return false;

        managedPhotos.findFirst();
        while (!managedPhotos.last()) {
            if (managedPhotos.retrieve().getPath().compareToIgnoreCase(path) == 0) {
                return true; // Photo already exists
            }
            managedPhotos.findNext();
        }

        // Check the last photo in the list
        return managedPhotos.retrieve().getPath().compareToIgnoreCase(path) == 0;
    }

    // Remove a photo by its path if it exists
    public void deletePhoto(String path) {
        
     // Return early if the list is empty or photo doesn't exist
    if (managedPhotos.empty() || !isPhotoExist(path)) {
        return;
    }
          // Traverse the list to find and delete the photo
            managedPhotos.findFirst();
            while ( !managedPhotos.last()) {
                Photo current  = managedPhotos.retrieve();
                if (current .getPath().compareToIgnoreCase(path) == 0) {
                    managedPhotos.remove(); // Remove the photo from the list
                      return; // Photo removed, exit
                }
                managedPhotos.findNext();
            }

             // Check and delete the last photo (if it matches)
             if (managedPhotos.retrieve().getPath().equalsIgnoreCase(path)) {
             managedPhotos.remove();
             }
        
    }

    
}