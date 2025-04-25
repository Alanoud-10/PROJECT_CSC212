
package csc212;

import java.util.Scanner;

public class Album {

        private String name;
        private String condition;
        private PhotoManager manager;
        private InvIndexPhotoManager InvIndexmanager;
        private int NumberofComps;

        // Constructor
        public Album(String name, String condition, PhotoManager manager, InvIndexPhotoManager InvIndexmanager)
        {
            this.name = name;
            this.condition = condition;
            this.manager = manager;
            this.InvIndexmanager = InvIndexmanager;
            NumberofComps =0 ;
        }

        // Return the name of the album
        public String getName()
        {
            return name;
        }
        
        // Return the condition associated with the album
        public String getCondition()
        {
            return condition;
        }

        // Return the manager
        public PhotoManager getManager()
        {
            return manager;
        }

         public InvIndexPhotoManager InvIndexmanager()
        {
            return InvIndexmanager;
        }
        
          // Return the number of tag comparisons used to find all photos of the album
        public int getNbComps()
        {
            return NumberofComps;
        }

           public LinkedList<Photo> getPhotos()
        {
            int choice = menu();
            
            LinkedList<Photo> result = new LinkedList<Photo>();
            switch (choice )
            {
                case 1:
                    result = getPhotoslinkedlist();
                    break;
                default:
                    result = getPhotosBSTree();
        }
        return result;
        }


        // Return all photos that satisfy the album condition
        public LinkedList<Photo> getPhotoslinkedlist()
        {
                LinkedList<Photo> Rphotos = new LinkedList<Photo>();
                {
                    LinkedList<Photo> photos1 = manager.getPhotos();
                    if (! photos1.empty())
                    {
                        photos1.findFirst();
                        while (! photos1.last())
                        {
                            Rphotos.insert(new Photo(photos1.retrieve().getPath(), photos1.retrieve().getTags()));
                            photos1.findNext();
                        }
                        Rphotos.insert(new Photo(photos1.retrieve().getPath(), photos1.retrieve().getTags()));
                    }
                }
                NumberofComps =0 ;
                
                if (this.condition.compareTo("") != 0)
                { //يقارن التاقات اللي موجوده بالاري حقتي مع التاقات اللي داخل كل صوره باللكندليست
                    // عشان يتاكد اذا ينطبق عليهم الشرط
                    String [] Array = condition.split(" AND ");
                    
                    Rphotos.findFirst();
                    while ( ! Rphotos.last())// n= يتكرر حسب عدد الصور وهي  
                    {
                        Photo photo = Rphotos.retrieve();
                        
                        if ( ! isexisting (photo.photoTags , Array ))// يتكرر حسب عدد التاقات t^2 
                            Rphotos.remove();
                        else
                            Rphotos.findNext();
                    }
                    Photo photo11 = Rphotos.retrieve();
                    //System.out.println("testlast " + photo11.getPath());
                    if ( ! isexisting (photo11.photoTags , Array ))
                        Rphotos.remove();
                    else
                        Rphotos.findNext();
                }
                return Rphotos; // the big o will be n*t^2
        }
       
      


          // this method to compare the tags in array(condition) whith linkedlist 
        private boolean isexisting ( LinkedList<String> AllTags , String [] Array )
        {
            boolean thecondition = true;
            if (AllTags.empty())
                thecondition = false; // if there is no tags
            else
            {
                for ( int i = 0 ; i < Array.length && thecondition ; i++)
                {
                    boolean find_tag = false;

                    AllTags.findFirst();

                    while (!AllTags.last())
                    {
                        this.NumberofComps ++ ;    
                        
                        if (AllTags.retrieve().compareToIgnoreCase(Array[i]) == 0)
                        {
                            find_tag = true;
                            break;
                        }
                        AllTags.findNext();
                    }
                    if (! find_tag )
                    {
                        this.NumberofComps ++ ;
                        
                        if (AllTags.retrieve().compareToIgnoreCase(Array[i]) == 0)
                            find_tag = true;
                    }
                    if ( ! find_tag )
                        thecondition = false;
                }
            }
            return thecondition;
        }
       
       //BST THAT will Return all photos that satisfy the album condition
        private LinkedList<Photo> getPhotosBSTree()
        {
            BST<LinkedList<Photo>> photosBSTree = InvIndexmanager.getPhotos();
            LinkedList<Photo> Rphotos = new LinkedList<Photo>();
            NumberofComps =0 ;
            String [] tags;
             if (this.condition.compareTo("") == 0)
            {
                if ( photosBSTree.findkey(" ") == true)
                    Rphotos = photosBSTree.retrieve();
            }
            else
            {
                tags = condition.split(" AND ");
                for ( int i = 0 ; i < tags.length ; i++)
                {
                    if ( photosBSTree.findkey(tags[i]) == true)
                    {
                        if (i == 0)
                        {
                            LinkedList<Photo > miniTag = photosBSTree.retrieve();
                            miniTag.findFirst();
                            while ( ! miniTag.last())
                            {
                                Rphotos.insert(miniTag.retrieve());
                                miniTag.findNext();
                            }
                            Rphotos.insert(miniTag.retrieve());
                        }
                        else
                            Rphotos  = Function ( Rphotos , photosBSTree.retrieve());
                    }
                    else
                    {
                        Rphotos = new LinkedList<Photo>();
                        break;
                    }
                }
            }
            return Rphotos;
        }


  // used when there is a condition 
        private LinkedList<Photo> Function ( LinkedList<Photo> list1 ,LinkedList<Photo> list2)
        {
            LinkedList<Photo> result = new LinkedList<Photo>();
            
            // empty list with no photos
            if ( list1.empty())
                return result;
            
            if (list2.empty())
                return list1;
            
            list2.findFirst();
            while (! list2.last())
            {
                boolean found = false;
                list1.findFirst();
                while (! list1.last() && ! found)
                {
                    NumberofComps++;
                    if (list2.retrieve().getPath().compareToIgnoreCase(list1.retrieve().getPath()) == 0)
                        found = true;
                    list1.findNext();
                }
  if (! found )
                {
                    NumberofComps++;
                    if (list2.retrieve().getPath().compareToIgnoreCase(list1.retrieve().getPath()) == 0)
                        found = true;
                }
                if (found )
                    result.insert(list2.retrieve());

                list2.findNext();
            }
            boolean found = false;
            list1.findFirst();
            while (! list1.last() && ! found)
            {
                NumberofComps++;
                if (list2.retrieve().getPath().compareToIgnoreCase(list1.retrieve().getPath()) == 0)
                    found = true;
                list1.findNext();
            }
            if (! found )
            {
                NumberofComps++;
                if ( list2.retrieve().getPath().compareToIgnoreCase(list1.retrieve().getPath()) == 0)
                    found = true;
            }
            if (found )
                result.insert(list2.retrieve());
                                  
            return result;
        }
        
          private int menu()
        {
            Scanner input = new Scanner ( System.in);
                int choice;
                System.out.println("1. Linked List");
                System.out.println("2. BST");
                System.out.println("Enter your choice ");
                choice = input.nextInt();
                
                return choice;
            }
            
        

    
}
