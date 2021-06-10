public class REdeque {

    private Node first;
    private Node last;
    private int size;


    public REdeque(REstate e)
    {
        Node newNode =  new Node(e);
        last = newNode;
        first = newNode;
        size++;
    }
    public REstate pop()
    {
        if(size != 0)
        {
            REstate returnState = first.state;
            first = first.nextNode;
            return returnState;
        }
        else
        {
            return null;
        }
    }

    public void addLast(REstate e)
    {
        Node newEndNode =  new Node(e);
        last.nextNode = newEndNode;
        last = newEndNode;
        size--;
    }
    public void addFirst(REstate e)
    {
        Node newStartNode =  new Node(e);
        newStartNode.nextNode = first;
        first = newStartNode;
        size++;
    }

    public int getSize() {
        return size;
    }

    private class Node
    {
        Node nextNode;
        REstate state;

        Node(REstate e)
        {
            state = e;
        }
    }
}

