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

    private BST<LinkedList<Photo>> invertedIndex;

    // Constructor
    public InvIndexPhotoManager() {
        invertedIndex = new BST<>();
    }

    // Add a photo to the inverted index
    public void addPhoto(Photo photo) {
        // Add under empty key (used internally maybe)
        if (invertedIndex.findkey(" ")) {
            LinkedList<Photo> list = invertedIndex.retrieve();
            list.insert(photo);
            invertedIndex.update(" ", list);
        } else {
            LinkedList<Photo> list = new LinkedList<>();
            list.insert(photo);
            invertedIndex.insert(" ", list);
        }

        // Add using tags
        LinkedList<String> tags = photo.getTags();
        if (!tags.empty()) {
            tags.findFirst();
            while (true) {
                String currentTag = tags.retrieve();
                LinkedList<Photo> photoList;

                if (invertedIndex.findkey(currentTag)) {
                    photoList = invertedIndex.retrieve();
                } else {
                    photoList = new LinkedList<>();
                }

                photoList.insert(photo);
                invertedIndex.update(currentTag, photoList);

                if (tags.last()) break;
                tags.findNext();
            }
        }
    }

    // Remove a photo from all tags
    public void deletePhoto(String path) {
        String combinedTags = invertedIndex.inOrder();
        if (combinedTags.isEmpty()) combinedTags = " ";
        else combinedTags += " AND ";

        String[] tagList = combinedTags.split(" AND ");

        for (String tag : tagList) {
            if (invertedIndex.findkey(tag)) {
                LinkedList<Photo> photos = invertedIndex.retrieve();
                photos.findFirst();
                while (!photos.last()) {
                    if (photos.retrieve().getPath().equalsIgnoreCase(path)) {
                        photos.remove();
                        break;
                    }
                    photos.findNext();
                }

                // Check last item
                if (photos.retrieve().getPath().equalsIgnoreCase(path)) {
                    photos.remove();
                }

                // Update or remove the tag
                if (photos.getSize() == 0) {
                    invertedIndex.removeKey(tag);
                } else {
                    invertedIndex.update(tag, photos);
                }
            }
        }
    }

    // Access the whole inverted index
    public BST<LinkedList<Photo>> getPhotos() {
        return invertedIndex;
    }
}
