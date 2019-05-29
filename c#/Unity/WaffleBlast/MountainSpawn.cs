using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class MountainSpawn : MonoBehaviour {

    public GameObject[] mountains;
    private Transform player;
    private int mountainCount = 8;
    private int x = 0;
    private Queue<GameObject> queue;

    // Use this for initialization
    void Start()
    {

        queue = new Queue<GameObject>();
        player = GameObject.FindGameObjectWithTag("Player").transform;
        for (int i = 0; i < mountainCount; i++)
        {
            Spawn();
        }
    }

    // Update is called once per frame
    void Update()
    {
        if (player.position.x + 250 > x)
        {
            Spawn();
        }
        if (queue.Count > mountainCount)
        {
            GameObject g = queue.Dequeue();
            GameObject.Destroy(g);
        }
    }

    private void Spawn()
    {
        GameObject g;
        g = Instantiate(mountains[Random.Range(0, 2)]) as GameObject;
        int y = Random.Range(-20, 0);
        int z = Random.Range(45, 70);
        g.transform.SetParent(transform);
        g.transform.position = new Vector3(x, y, z);
        g.transform.localScale = new Vector3(1 + z / 10, 1 + z / 10, 1);
        x += Random.Range(45, 85);
        queue.Enqueue(g);
    }
}
