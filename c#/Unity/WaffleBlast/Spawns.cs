using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Spawns : MonoBehaviour {

    public GameObject coin;
    public GameObject syrup;
    public GameObject egg;

    private string[] spawner;
    private Transform player;
    private int spawnCount = 15;
    private int x = 0;
    private Queue<GameObject> queue;

    // Use this for initialization
    void Start()
    {

        spawner = new string[3];
        spawner[0] = "egg";
        spawner[1] = "egg";
        spawner[2] = "egg";
 
        queue = new Queue<GameObject>();
        player = GameObject.FindGameObjectWithTag("Player").transform;
        for (int i = 0; i < spawnCount; i++)
        {
            Spawn();
        }
    }

    // Update is called once per frame
    void Update()
    {
        if (player.position.x + 100 > x)
        {
            Spawn();
        }
        if (queue.Count > spawnCount)
        {
            GameObject g = queue.Dequeue();
            GameObject.Destroy(g);
        }
    }

    void Spawn()
    {
        int spawn = Random.Range(0, spawner.Length);
        if(spawner[spawn] == "coin")
        {
            SpawnCoin();
        }
        if(spawner[spawn] == "syrup")
        {
            SpawnSyrup();
        }
        if(spawner[spawn] == "egg")
        {
            SpawnEgg();
        }
    }

    private void SpawnSyrup()
    {
        GameObject g;
        g = Instantiate(syrup) as GameObject;
        float y = -2.75f;
        float z = 0f;
        g.transform.SetParent(transform);
        g.transform.localScale = new Vector3(1, 1, 1);
        g.transform.position = new Vector3(x, y, z);
        x += Random.Range(20, 55);
        queue.Enqueue(g);
    }

    private void SpawnCoin()
    {
        GameObject g;
        g = Instantiate(coin) as GameObject;
        int y = Random.Range(0, 150);
        int z = 0;
        g.transform.SetParent(transform);
        g.transform.position = new Vector3(x, y, z);
        x += Random.Range(10, 35);
        queue.Enqueue(g);
    }

    private void SpawnEgg()
    {
        GameObject g;
        g = Instantiate(egg) as GameObject;
        int y = -3;
        int z = 0;
        g.transform.SetParent(transform);
        g.transform.position = new Vector3(x, y, z);
        g.transform.localScale = new Vector3(0.5f, 0.5f, 1);
        x += Random.Range(20, 55);
        queue.Enqueue(g);
    }
}
