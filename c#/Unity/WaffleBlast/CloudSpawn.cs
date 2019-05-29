using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class CloudSpawn : MonoBehaviour {

    public GameObject[] clouds;
    private Transform player;
    private int cloudCount = 16;
    private int x = 0;
    private Queue<GameObject> queue;

    // Use this for initialization
    void Start()
    {

        queue = new Queue<GameObject>();
        player = GameObject.FindGameObjectWithTag("Player").transform;
        for (int i = 0; i < cloudCount; i++)
        {
            Spawn();
        }
    }

    // Update is called once per frame
    void Update()
    {
        if (player.position.x + 120 > x)
        {
            Spawn();
        }
        if (queue.Count > cloudCount)
        {
            GameObject g = queue.Dequeue();
            GameObject.Destroy(g);
        }
    }

    private void Spawn()
    {
        GameObject g;
        g = Instantiate(clouds[Random.Range(0,3)]) as GameObject;
        int y = Random.Range(20, 150);
        int z = Random.Range(20, 70);
        g.transform.SetParent(transform);
        g.transform.position = new Vector3(x, y, z);
        g.transform.localScale = new Vector3(2 + z / 15, 2 + z / 15, 1);
        x += Random.Range(15, 35);
        queue.Enqueue(g);
    }
}
