using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class FloorSpawn : MonoBehaviour {

    public GameObject tile;
    private Transform playerTransform;
    private float spawnX = 0.0f;
    private float tileLength = 16f;
    private int tileCount = 3;
    private Queue<GameObject> queue; 

	// Use this for initialization
	void Start () {

        queue = new Queue<GameObject>();
        playerTransform = GameObject.FindGameObjectWithTag("Player").transform;
        for(int i = 0; i < tileCount; i++)
        {
            Spawn();
        }
	}
	
	// Update is called once per frame
	void Update () {
		if(playerTransform.position.x > (spawnX - tileCount * tileLength))
        {
            Spawn();
        }
        if(queue.Count > tileCount + 2)
        {
            GameObject g = queue.Dequeue();
            GameObject.Destroy(g);
        }
	}

    private void Spawn(int prefabIndex = -1)
    {
        GameObject g;
        g = Instantiate(tile) as GameObject;
        g.transform.SetParent(transform);
        g.transform.position = new Vector3(spawnX, g.transform.position.y, g.transform.position.z);
        spawnX += tileLength;
        queue.Enqueue(g);
    }
    
}
