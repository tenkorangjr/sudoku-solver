/*
 * Name: Michael Tenkorang
 * Class Purpose: Working with the Stacks Abstract Data Structure, DFS and Backtracking
 */

public interface Stack<E> {
    /*
     * Adds item to the list of items
     */
    public void push(E item);

    /*
     * Return the value of the last added item
     */
    public E peek();

    /*
     * Retern the value of the last added item and remove from list
     */
    public E pop();

    /*
     * Return size of the Stack
     */
    public int size();

    /*
     * Check if stack is empty
     */
    public boolean isEmpty();
}
