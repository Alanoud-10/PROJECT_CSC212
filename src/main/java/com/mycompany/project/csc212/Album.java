
package csc212;

public class Album {

        private String name;
        private String condition;
        private PhotoManager manager;
        private int NumberofComps;

        // Constructor
        public Album(String name, String condition, PhotoManager manager)
        {
            this.name = name;
            this.condition = condition;
            this.manager = manager;
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
        
        // Return all photos that satisfy the album condition
        public LinkedList<Photo> getPhotos()
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
                        //System.out.println("test " + photo.getPath());
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
       
        // Return the number of tag comparisons used to find all photos of the album
        public int getNbComps()
        {
            return NumberofComps;
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
                        //System.out.println(AllTags.retrieve() + " " + Array[i]);
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
                        //System.out.println(AllTags.retrieve() + " " + Array[i]);
                        if (AllTags.retrieve().compareToIgnoreCase(Array[i]) == 0)
                            find_tag = true;
                    }
                    if ( ! find_tag )
                        thecondition = false;
                }
            }
            return thecondition;
        }
       

    
}
