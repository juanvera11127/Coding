using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class particle : MonoBehaviour
{

    Rigidbody rb;
    private float speed;
    int rand;

    // Use this for initialization
    void Start()
    {
        rb = gameObject.GetComponent<Rigidbody>();
        rand = Random.Range(-1, 2);
        while(rand == 0)
        {
            rand = Random.Range(-1, 2);
        }
        rb.position = new Vector3(rand * Random.Range(3, 10), Random.Range(-2, -9), 30);
        rb.rotation = Quaternion.identity;
    }

    // Update is called once per frame
    void Update()
    {
        speed = Player.speed*3 / (Player.isSlow ? 2 : 1);
        
        if (rb.position.z <= -10f)
        {
            gameObject.transform.position = new Vector3(rand * Random.Range(3, 6), Random.Range(2, -6), 40);
        }
        rb.velocity = new Vector3(0, 0, -speed);
    }
}