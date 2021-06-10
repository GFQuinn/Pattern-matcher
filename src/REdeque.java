public class REdeque {

    private Node first;
    private Node last;
    private int size;


    public REdeque(REstate e)
    {
        Node newNode =  new Node(e);
        last = newNode;
        first = newNode;
        size = 1;
    }
    public REstate pop()
    {
        if(size != 0)
        {
            REstate returnState = first.state;
            size--;
            if(size == 0)
            {
               first = null;
               last = null;
            }
            else
            {
                first = first.nextNode;
            }
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
            if (size == 0)
            {
                first = newEndNode;
                last = newEndNode;
            }
            else {
                last.nextNode = newEndNode;
                last = newEndNode;
            }
            size++;
    }
    public void addFirst(REstate e)
    {

            Node newStartNode =  new Node(e);
            if (size == 0)
            {
                first = newStartNode;
                last = newStartNode;
            }
            else
            {
                newStartNode.nextNode = first;
                first = newStartNode;
            }
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

