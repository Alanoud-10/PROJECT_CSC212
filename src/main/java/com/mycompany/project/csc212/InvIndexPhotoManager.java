/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.project.csc212;

/**
 *
 * @author alanoud
 */
public class InvIndexPhotoManager {
    BST<LinkedList<Photo>> invIndex;

    // Constructor
    public InvIndexPhotoManager() {
        invIndex = new BST<LinkedList<Photo>>();
    }

    // Add a photo
    public void insertPhoto(Photo p) {
        LinkedList<String> tags = p.getTags();
        if (!tags.empty()) {
            tags.findFirst();
            while (!tags.last()) {
                if (invIndex.findkey(tags.retrieve())) {
                    LinkedList<Photo> photosList = invIndex.retrieve();
                    photosList.insert(p);
                    invIndex.update(tags.retrieve(), photosList);
                } else {
                    LinkedList<Photo> photosList = new LinkedList<Photo>();
                    photosList.insert(p);
                    invIndex.insert(tags.retrieve(), photosList);
                }
                tags.findNext();
            }
            if (invIndex.findkey(tags.retrieve())) {
                LinkedList<Photo> photosList = invIndex.retrieve();
                photosList.insert(p);
                invIndex.update(tags.retrieve(), photosList);
            } else {
                LinkedList<Photo> photosList = new LinkedList<Photo>();
                photosList.insert(p);
                invIndex.insert(tags.retrieve(), photosList);
            }
        }
    }

    // Delete a photo
    public void removePhoto(String path) {
    String allTags = invIndex.inOrder();
    String[] tags = allTags.split(" AND ");

    for (int i = 0; i < tags.length; i++) {
        if (invIndex.findkey(tags[i])) {
            LinkedList<Photo> photosList = invIndex.retrieve();
            photosList.findFirst();
            while (!photosList.last()) {
                if (photosList.retrieve().getPath().compareToIgnoreCase(path) == 0) {
                    photosList.remove();
                    break;
                } else {
                    photosList.findNext();
                }
            }
            if (photosList.retrieve().getPath().compareToIgnoreCase(path) == 0) {
                photosList.remove();
            }

            // هنا التعديل الصحيح
            if (photosList.empty()) {
                invIndex.removeKey(tags[i]);
            } else {
                invIndex.update(tags[i], photosList);
            }
        }
    }
}

    // Return the inverted index of all managed photos
    public BST<LinkedList<Photo>> getIndex() {
        return invIndex;
    }
}

