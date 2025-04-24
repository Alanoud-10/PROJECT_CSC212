/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.project.csc212;


/**
 *
 * @author alanoud
 */

public class Photo {

    private String imagePath;
    LinkedList<String> photoTags = new LinkedList<>();

    // Constructor
    public Photo(String path, LinkedList<String> tags) {
        this.imagePath = path;
        copyTags(tags,photoTags);// Copy tags from input to internal list
    }

    // Return the full file name (the path) of the photo.
    public String getPath() {
        return imagePath;
    }

    // Return all tags associated with the photo
    public LinkedList<String> getTags() {
        LinkedList<String> tagsCopy = new LinkedList<>();
        copyTags(photoTags, tagsCopy); // Copy tags to avoid external modification
        return tagsCopy;
    }

    // Helper method to copy tags from one LinkedList to another
    private void copyTags(LinkedList<String> from, LinkedList<String> to) {
    if (!from.empty()) {
        from.findFirst();
        while (!from.last()) {
            to.insert(from.retrieve()); // Copy all except the last tag
            from.findNext();
        }
        to.insert(from.retrieve());// Add the last tag
    }
}

    // Override toString() to represent photo object as a string
    @Override
    public String toString() {
        String str = "Photo{" + "path=" + imagePath + ", allTags=";

        if (!photoTags.empty()) {
            photoTags.findFirst();
            while (!photoTags.last()) {
                str += photoTags.retrieve() + "; ";
                photoTags.findNext();
            }
            str += photoTags.retrieve();
        }

        str += "}";
        return str;
    }
}
