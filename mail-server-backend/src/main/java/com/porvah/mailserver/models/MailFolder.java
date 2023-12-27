package com.porvah.mailserver.models;

import com.porvah.mailserver.enums.RequiredPage;
import com.porvah.mailserver.enums.SortType;
import com.porvah.mailserver.interfaces.ROMail;
import com.porvah.mailserver.models.sortStrategy.AscendingSort;
import com.porvah.mailserver.models.sortStrategy.DescendingSort;
import com.porvah.mailserver.models.sortStrategy.SortStrategy;

import java.util.*;

public class MailFolder<T extends ROMail> {
    private final String name;
    protected final List<T> mails;
    private PriorityQueue<T> mailsWithsPriority;
    public final FolderIterator<T> iterator;

    public MailFolder(String name){
        this.name = name;
        this.mails = new ArrayList<T>();
        this.mailsWithsPriority = new PriorityQueue<T>((Comparator.comparingInt(T::getPriority)).reversed());
        this.iterator = new FolderIterator<T>(this, 10);
    }
    public List<T> getPage(RequiredPage req, SortType sort){
        if(req == RequiredPage.NEXT) return this.iterator.getNext(sort);
        else if(req == RequiredPage.PREV) return this.iterator.getPrev((sort));
        else return this.iterator.reset(sort);
    }
    public String getName(){
        return this.name;
    }
    public void addMail(T mail){
        this.mails.add(mail);
        this.mailsWithsPriority.add(mail);
    }
    public void removeMail(int id){
        for(T mail : this.mails){
            if(mail.getId() == id){
                this.mails.remove(mail);
                break;
            }
        }
        this.mailsWithsPriority = removeFromQ(this.mailsWithsPriority, id);
    }
    protected List<T> getMails(SortType sort) {
        List<T> result = new ArrayList<T>(this.mails);
        if (sort == SortType.DESCEND) {
            SortStrategy<T> strategy = new DescendingSort<>();
            return strategy.sort(result);
        } else if (sort == SortType.PRIORITY){
            return this.QtoList(this.mailsWithsPriority);
        }else if (sort == SortType.ASCEND){
            SortStrategy<T> strategy = new AscendingSort<>();
            return strategy.sort(result);
        }
        return result;
    }
    private List<T> QtoList(PriorityQueue<T> Q){
        PriorityQueue<T> clone = new PriorityQueue<>(Q);
        List<T> newList = new ArrayList<T>();
        while(!clone.isEmpty()) newList.add(clone.poll());
        return newList;
    }

    private PriorityQueue<T> removeFromQ(PriorityQueue<T> Q, int id){
        PriorityQueue<T> result = new PriorityQueue<T>(Comparator.comparingInt(T::getPriority).reversed());
        for( T mail : Q){
            if(mail.getId() != id) result.add(mail);
        }
        return result;
    }
    public boolean contains(int id){
        try{
            this.getMail(id);
            return true;
        } catch (Exception e){
            return false;
        }
    }
    public T getMail(int id){
        for(T mail : this.mails){
            if(mail.getId() == id){
                return mail;
            }
        }
        throw new RuntimeException("mail not found");
    }
}
